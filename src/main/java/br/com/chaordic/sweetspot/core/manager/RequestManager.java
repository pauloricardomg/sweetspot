package br.com.chaordic.sweetspot.core.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import br.com.chaordic.sweetspot.core.exception.RequestExistsException;
import br.com.chaordic.sweetspot.core.pojo.CreateRequest;
import br.com.chaordic.sweetspot.core.pojo.RequestInfo;
import br.com.chaordic.sweetspot.core.pojo.SweetSpotRequest;

import com.google.common.base.Optional;

public class RequestManager {

    private static String DEFAULT_PRICE = "0.1";

    private final Map<String, SweetSpotRequest> requestMap;
    private final Properties maxBids;

    private AWSManager awsManager;

    public RequestManager(AWSManager awsManager, Properties maxBids) {
        this.requestMap = new HashMap<>();
        this.maxBids = maxBids;
        this.awsManager = awsManager;
    }

    public synchronized void create(CreateRequest request) throws RequestExistsException {
        if (requestMap.containsKey(request.getId())) {
            throw new RequestExistsException(request.getId());
        }


        SweetSpotRequest sweetReq = new SweetSpotRequest(request.getId(),
                                                        request.getNumInstances(),
                                                        request.getInstanceType(),
                                                        getMaxBid(request.getInstanceType()),
                                                        request.getInstanceNamePrefix(),
                                                        request.getInstanceTags(),
                                                        request.getAmi(),
                                                        request.getZone(),
                                                        request.getGroup(),
                                                        request.getBlockDeviceMappings());
        awsManager.register(sweetReq);
        requestMap.put(request.getId(), sweetReq);
    }

    public Optional<RequestInfo> getInfo(String requestId) {
        SweetSpotRequest request = requestMap.get(requestId);
        if (request == null) {
            return Optional.absent();
        }
        return Optional.of(request.getInfo());
    }

    private Float getMaxBid(String instanceType) {
        return Float.valueOf(maxBids.getProperty(instanceType, DEFAULT_PRICE));
    }

    public Set<String> getRequestIds() {
        return this.requestMap.keySet();
    }

}
