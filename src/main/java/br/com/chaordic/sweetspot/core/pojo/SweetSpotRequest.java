package br.com.chaordic.sweetspot.core.pojo;

import java.util.HashMap;
import java.util.Map;

import br.com.chaordic.sweetspot.core.util.CloudUtils;

public class SweetSpotRequest {

    private final String id;
    private final int numInstances;
    private final String zone;
    private final String ami;
    private final String group;
    private final String instanceType;
    private final String instanceNamePrefix;
    private final Map<String, String> instanceTags;
    private final Map<String, String> blockDeviceMappings;
    private Float maxBid;

    private final Map<String, NodeInfo> nodeInfos = new HashMap<>();

    private RequestStatus status;
    private String errorMessage;

    public SweetSpotRequest(String id, int numInstances, String instanceType,
            Float maxBid, String instanceNamePrefix, Map<String, String> instanceTags,
            String ami, String zone, String group, Map<String, String> blockDeviceMappings) {
        this.id = id;
        this.numInstances = numInstances;
        this.instanceType = instanceType;
        this.maxBid = maxBid;
        this.instanceNamePrefix = instanceNamePrefix;
        this.instanceTags = instanceTags;
        this.ami = CloudUtils.normalizeAmi(zone, ami);
        this.zone = zone;
        this.group = group;
        this.status = RequestStatus.OPEN;
        this.blockDeviceMappings = blockDeviceMappings;
    }

    public RequestInfo getInfo() {
        return new RequestInfo(id, numInstances, instanceType, maxBid, instanceNamePrefix,
                               instanceTags, status, ami, zone, group, errorMessage,
                               nodeInfos.values(), blockDeviceMappings);
    }

    public Float getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(Float maxBid) {
        this.maxBid = maxBid;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public int getNumInstances() {
        return numInstances;
    }

    public String getZone() {
        return zone;
    }

    public String getAmi() {
        return ami;
    }

    public String getGroup() {
        return group;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public String getInstanceNamePrefix() {
        return instanceNamePrefix;
    }

    public Map<String, String> getInstanceTags() {
        return instanceTags;
    }

    public void updateInfo(NodeInfo nodeInfo) {
        this.nodeInfos.put(nodeInfo.getId(), nodeInfo);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String message) {
        this.errorMessage = message;
    }

    public Map<String, String> getBlockDeviceMappings() {
        return blockDeviceMappings;
    }
}
