package br.com.chaordic.sweetspot.core.pojo;

import java.util.Set;

import org.jclouds.compute.domain.NodeMetadata;
import org.jclouds.compute.domain.NodeMetadata.Status;

public class NodeInfo {

    private final String id;
    private final Status status;
    private final String hostname;
    private final Set<String> publicIps;

    public NodeInfo(NodeMetadata nodeMetadata) {
        this.id = nodeMetadata.getId();
        this.status = nodeMetadata.getStatus();
        this.hostname = nodeMetadata.getHostname();
        this.publicIps = nodeMetadata.getPublicAddresses();
    }

    public Status getStatus() {
        return status;
    }

    public String getHostname() {
        return hostname;
    }

    public Set<String> getPublicIps() {
        return publicIps;
    }

    public String getId() {
        return this.id;
    }
}
