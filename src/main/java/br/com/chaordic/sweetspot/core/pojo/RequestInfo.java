package br.com.chaordic.sweetspot.core.pojo;

import java.util.Map;

public class RequestInfo {

    private final String id;
    private final int numInstances;
    private final String instanceType;
    private final String instanceNamePrefix;
    private final Map<String, String> instanceTags;
    private final RequestStatus status;
    private final Double maxBid;

    public RequestInfo(String id, int numInstances, String instanceType,
            Double maxBid, String instanceNamePrefix, Map<String, String> instanceTags,
            RequestStatus status) {
        this.id = id;
        this.numInstances = numInstances;
        this.instanceType = instanceType;
        this.maxBid = maxBid;
        this.instanceNamePrefix = instanceNamePrefix;
        this.instanceTags = instanceTags;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public int getNumInstances() {
        return numInstances;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public Double getMaxBid() {
        return maxBid;
    }

    public String getInstanceNamePrefix() {
        return instanceNamePrefix;
    }

    public Map<String, String> getInstanceTags() {
        return instanceTags;
    }

    public RequestStatus getStatus() {
        return status;
    }
}
