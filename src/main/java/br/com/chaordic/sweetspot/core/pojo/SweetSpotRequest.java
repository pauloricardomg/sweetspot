package br.com.chaordic.sweetspot.core.pojo;

import java.util.Map;

public class SweetSpotRequest {

    private final String id;
    private final int numInstances;
    private final String instanceType;
    private final String instanceNamePrefix;
    private final Map<String, String> instanceTags;
    private RequestStatus status;
    private Double maxBid;

    public SweetSpotRequest(String id, int numInstances, String instanceType,
            Double maxBid, String instanceNamePrefix, Map<String, String> instanceTags) {
        this.id = id;
        this.numInstances = numInstances;
        this.instanceType = instanceType;
        this.maxBid = maxBid;
        this.instanceNamePrefix = instanceNamePrefix;
        this.instanceTags = instanceTags;
        this.status = RequestStatus.OPEN;
    }

    public RequestInfo getInfo() {
        return new RequestInfo(id, numInstances, instanceType, maxBid, instanceNamePrefix,
                               instanceTags, status);
    }

}
