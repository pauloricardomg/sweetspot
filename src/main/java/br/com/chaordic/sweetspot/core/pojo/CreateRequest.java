package br.com.chaordic.sweetspot.core.pojo;

import java.util.Map;

public class CreateRequest {

    public String id;
    public int numInstances;
    public String instanceType;
    public String instanceNamePrefix;
    public Map<String, String> instanceTags;

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
}
