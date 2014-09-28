package br.com.chaordic.sweetspot;

import io.dropwizard.Configuration;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SweetSpotConfiguration extends Configuration {
    @JsonProperty("aws")
    private Map<String, String> awsConfiguration;

    @JsonProperty("chef")
    private Map<String, String> chefConfiguration;

    @JsonProperty("max_bids")
    private Map<String, String> maxBids;

    public Map<String, String> getAwsConfiguration() {
        return awsConfiguration;
    }

    public void setAwsConfiguration(Map<String, String> awsConfiguration) {
        this.awsConfiguration = awsConfiguration;
    }

    public Map<String, String> getChefConfiguration() {
        return chefConfiguration;
    }

    public void setChefConfiguration(Map<String, String> chefConfiguration) {
        this.chefConfiguration = chefConfiguration;
    }

    public Map<String, String> getMaxBids() {
        return maxBids;
    }

    public void setMaxBids(Map<String, String> maxBids) {
        this.maxBids = maxBids;
    }
}
