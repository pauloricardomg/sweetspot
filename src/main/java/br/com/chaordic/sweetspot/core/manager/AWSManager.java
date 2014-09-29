package br.com.chaordic.sweetspot.core.manager;

import io.dropwizard.lifecycle.setup.LifecycleEnvironment;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutorService;

import org.jclouds.aws.ec2.compute.AWSEC2TemplateOptions;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.RunNodesException;
import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.compute.domain.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.chaordic.sweetspot.core.configuration.AWSConfiguration;
import br.com.chaordic.sweetspot.core.pojo.NodeInfo;
import br.com.chaordic.sweetspot.core.pojo.RequestStatus;
import br.com.chaordic.sweetspot.core.pojo.SweetSpotRequest;
import br.com.chaordic.sweetspot.core.tasks.SubmitSpotRequests;
import br.com.chaordic.sweetspot.core.util.CloudUtils;

import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import com.google.common.io.Files;

public class AWSManager implements SpotInstancesManager {

    private static final String AWS_EXECUTOR_NAME = "EC2_EXECUTOR";
    private final ExecutorService awsExecutor;
    private final ComputeService compute;
    private final String sshPublicKey;

    static final Logger logger = LoggerFactory.getLogger(AWSManager.class);

    public AWSManager(LifecycleEnvironment lifecycleEnvironment, AWSConfiguration configuration) {
        try {
            this.sshPublicKey = Files.toString(new File(configuration.getSshPublicKeyFile()), Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Could not load ssh public key from file: %s",
                                                        configuration.getSshPublicKeyFile()));
        }
        this.awsExecutor = lifecycleEnvironment.executorService(AWS_EXECUTOR_NAME).maxThreads(configuration.getMaxExecutorThreads()).build();
        this.compute = CloudUtils.initComputeService(configuration.getAccessKey(), configuration.getSecretKey());
    }

    public void register(SweetSpotRequest request) {
        awsExecutor.submit(new SubmitSpotRequests(this, request));
    }

    @Override
    public void submitSpotInstanceRequest(SweetSpotRequest request) {
        Optional<Template> template = buildTemplate(request);
        if (!template.isPresent()) {
            return;
        }

        Set<NodeMetadata> nodes = launchSpotRequestsAndWaitNodes(request, template.get());
        for (NodeMetadata nodeMetadata : nodes) {
            request.updateInfo(new NodeInfo(nodeMetadata));
        }
    }

    private Optional<Template> buildTemplate(SweetSpotRequest request) {
        try {
            Template template = compute.templateBuilder()
                    .imageId(request.getAmi())
                    .locationId(request.getZone())
                    .hardwareId(request.getInstanceType()).build();

            template.getOptions().as(AWSEC2TemplateOptions.class)
            .spotPrice(request.getMaxBid())
            .authorizePublicKey(this.sshPublicKey)
            .securityGroupIds(request.getGroup())
            .noPlacementGroup()
            .userMetadata(request.getInstanceTags())
            .blockDeviceMappings(CloudUtils.createBlockDeviceMappings(request.getBlockDeviceMappings()));
            return Optional.of(template);
        } catch (Exception e) {
            request.setStatus(RequestStatus.INVALID);
            request.setErrorMessage(e.getMessage());
        }

        return Optional.absent();
    }

    private Set<NodeMetadata> launchSpotRequestsAndWaitNodes(SweetSpotRequest request, Template template) {
        Set<NodeMetadata> nodes = Sets.newHashSet();
        logger.info(">> running {} spot nodes type({}) with ami({}) in group({})", request.getNumInstances(), template.getHardware()
                .getProviderId(), template.getImage().getId(), request.getGroup());
        request.setStatus(RequestStatus.WAITING_SPOTS);
        try {
            nodes = (Set<NodeMetadata>)compute.createNodesInGroup(request.getId(), request.getNumInstances(), template);
            logger.info(">> successfully launched {} instances in group {}", nodes.size(), request.getId());
            request.setStatus(RequestStatus.ACTIVE);
        } catch (RunNodesException e) {
            logger.warn(">> spot request failed: {}", e.getMessage());
            request.setStatus(RequestStatus.SPOT_REQUEST_FAILED);
            request.setErrorMessage(e.getMessage());
            for (NodeMetadata node : e.getNodeErrors().keySet())
               compute.destroyNode(node.getId());
        }

        return nodes;
    }
}
