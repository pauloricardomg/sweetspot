package br.com.chaordic.sweetspot.core.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.chaordic.sweetspot.core.exception.RequestExistsException;
import br.com.chaordic.sweetspot.core.pojo.CreateRequest;
import br.com.chaordic.sweetspot.core.pojo.RequestInfo;
import br.com.chaordic.sweetspot.core.pojo.SweetSpotRequest;

import com.google.common.base.Optional;

public class RequestManager {

    private static Double DEFAULT_PRICE = 0.1;

    private final Map<String, SweetSpotRequest> requestMap;
    private final Map<String, String> maxBids;

    public RequestManager(Map<String, String> maxBids) {
        this.requestMap = new HashMap<>();
        this.maxBids = maxBids;
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
                                                        request.getInstanceTags());
        requestMap.put(request.getId(), sweetReq);
    }

    public Optional<RequestInfo> getInfo(String requestId) {
        SweetSpotRequest request = requestMap.get(requestId);
        if (request == null) {
            return Optional.absent();
        }
        return Optional.of(request.getInfo());
    }

    private Double getMaxBid(String instanceType) {
        String defaultInstancePrice = maxBids.get(instanceType);
        return defaultInstancePrice == null? DEFAULT_PRICE : Double.valueOf(defaultInstancePrice);
    }

    public Set<String> getRequestIds() {
        return this.requestMap.keySet();
    }

}
