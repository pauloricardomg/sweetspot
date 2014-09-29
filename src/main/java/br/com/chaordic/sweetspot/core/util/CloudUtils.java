package br.com.chaordic.sweetspot.core.util;

import static org.jclouds.compute.config.ComputeServiceProperties.TIMEOUT_SCRIPT_COMPLETE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.jclouds.ContextBuilder;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.ComputeServiceContext;
import org.jclouds.ec2.domain.BlockDeviceMapping;
import org.jclouds.enterprise.config.EnterpriseConfigurationModule;
import org.jclouds.logging.slf4j.config.SLF4JLoggingModule;
import org.jclouds.sshj.config.SshjSshClientModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;

public class CloudUtils {

    private static final String AWS_EC2_PROVIDER = "aws-ec2";

    static final Logger logger = LoggerFactory.getLogger(CloudUtils.class);

    public static ComputeService initComputeService(String accessKey, String secretKey) {
        Properties properties = new Properties();
        //properties.setProperty(PROPERTY_EC2_AMI_QUERY, "owner-id=137112412989;state=available;image-type=machine");
        //properties.setProperty(PROPERTY_EC2_CC_AMI_QUERY, "");
        long scriptTimeout = TimeUnit.MILLISECONDS.convert(20, TimeUnit.MINUTES);
        properties.setProperty(TIMEOUT_SCRIPT_COMPLETE, scriptTimeout + "");

        // example of injecting a ssh implementation
        Iterable<Module> modules = ImmutableSet.<Module>of(
              new SshjSshClientModule(),
              new SLF4JLoggingModule(),
              new EnterpriseConfigurationModule());

        ContextBuilder builder = ContextBuilder.newBuilder(AWS_EC2_PROVIDER)
                                               .credentials(accessKey, secretKey)
                                               .modules(modules)
                                               .overrides(properties);

        logger.info(">> initializing {}", builder.getApiMetadata());

        return builder.buildView(ComputeServiceContext.class).getComputeService();
     }

    public static String getRegion(String zone) {
        return zone.substring(0, zone.length()-1);
    }

    public static String normalizeAmi(String zone, String ami) {
        return ami.contains("/") ? ami : String.format("%s/%s", getRegion(zone), ami);
    }

    public static Collection<BlockDeviceMapping> createBlockDeviceMappings(Map<String, String> blockDeviceMappings) {
        ArrayList<BlockDeviceMapping> result = new ArrayList<>(blockDeviceMappings.size());
        for (Entry<String, String> entry : blockDeviceMappings.entrySet()) {
            result.add(new BlockDeviceMapping.MapEphemeralDeviceToDevice(entry.getKey(), entry.getValue()));
        }
        return result;
    }

}
