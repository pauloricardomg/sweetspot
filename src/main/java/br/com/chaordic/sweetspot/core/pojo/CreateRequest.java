package br.com.chaordic.sweetspot.core.pojo;

import java.util.Map;

public class CreateRequest {

    public String id;
    public int numInstances;
    public String instanceType;
    public String instanceNamePrefix;
    public String ami;
    public String group;
    public String zone;
    public Map<String, String> instanceTags;
    public Map<String, String> blockDeviceMappings;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public int getNumInstances() {
        return numInstances;
    }
    public void setNumInstances(int numInstances) {
        this.numInstances = numInstances;
    }

    public String getInstanceType() {
        return instanceType;
    }
    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getInstanceNamePrefix() {
        return instanceNamePrefix;
    }
    public void setInstanceNamePrefix(String instanceNamePrefix) {
        this.instanceNamePrefix = instanceNamePrefix;
    }

    public Map<String, String> getInstanceTags() {
        return instanceTags;
    }
    public void setInstanceTags(Map<String, String> instanceTags) {
        this.instanceTags = instanceTags;
    }
    public String getAmi() {
        return ami;
    }
    public void setAmi(String ami) {
        this.ami = ami;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public String getZone() {
        return zone;
    }
    public void setZone(String zone) {
        this.zone = zone;
    }
    public Map<String, String> getBlockDeviceMappings() {
        return blockDeviceMappings;
    }
    public void setBlockDeviceMappings(Map<String, String> blockDeviceMappings) {
        this.blockDeviceMappings = blockDeviceMappings;
    }
}
