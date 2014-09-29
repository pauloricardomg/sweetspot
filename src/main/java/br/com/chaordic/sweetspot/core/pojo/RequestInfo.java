package br.com.chaordic.sweetspot.core.pojo;

import java.util.Collection;
import java.util.Map;

public class RequestInfo {

    private final String id;
    private final int numInstances;
    private final String instanceType;
    private final String instanceNamePrefix;
    private final Map<String, String> instanceTags;
    private final RequestStatus status;
    private final Float maxBid;
    private final String ami;
    private final String zone;
    private final String group;
    private final Map<String, String> blockDeviceMappings;
    private final Collection<NodeInfo> instances;
    private final String errorMessage;

    public RequestInfo(String id, int numInstances, String instanceType,
            Float maxBid, String instanceNamePrefix, Map<String, String> instanceTags,
            RequestStatus status, String ami, String zone, String group,
            String errorMessage, Collection<NodeInfo> instances,
            Map<String,String> blockDeviceMappings) {

        this.id = id;
        this.numInstances = numInstances;
        this.instanceType = instanceType;
        this.maxBid = maxBid;
        this.instanceNamePrefix = instanceNamePrefix;
        this.instanceTags = instanceTags;
        this.status = status;
        this.ami = ami;
        this.zone = zone;
        this.group = group;
        this.errorMessage = errorMessage;
        this.instances = instances;
        this.blockDeviceMappings = blockDeviceMappings;
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

    public Float getMaxBid() {
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

    public String getAmi() {
        return ami;
    }

    public String getZone() {
        return zone;
    }

    public String getGroup() {
        return group;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Collection<NodeInfo> getInstances() {
        return instances;
    }

    public Map<String, String> getBlockDeviceMappings() {
        return blockDeviceMappings;
    }
}
