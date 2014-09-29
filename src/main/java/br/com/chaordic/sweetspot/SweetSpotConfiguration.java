package br.com.chaordic.sweetspot;

import io.dropwizard.Configuration;

import java.util.HashMap;
import java.util.Properties;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.chaordic.sweetspot.core.configuration.AWSConfiguration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SweetSpotConfiguration extends Configuration {

    @Valid
    @NotNull
    private AWSConfiguration awsConfiguration;

    private Properties chefConfiguration = new Properties();

    private Properties maxBids = new Properties();

    private Properties executorsConfiguration = new Properties();

    public AWSConfiguration getAwsConfiguration() {
        return awsConfiguration;
    }

    @JsonProperty("aws")
    public void setAwsConfiguration(AWSConfiguration awsConfiguration) {
        this.awsConfiguration = awsConfiguration;
    }

    public Properties getChefConfiguration() {
        return chefConfiguration;
    }

    @JsonProperty("chef")
    public void setChefConfiguration(HashMap<String, String> chefConfigurationMap) {
        this.chefConfiguration.putAll(chefConfigurationMap);
    }

    public Properties getMaxBids() {
        return maxBids;
    }

    @JsonProperty("max_bids")
    public void setMaxBids(HashMap<String, String> maxBidsMap) {
        this.maxBids.putAll(maxBidsMap);
    }

    public Properties getExecutorsConfiguration() {
        return executorsConfiguration;
    }

    @JsonProperty("executors")
    public void setExecutorsConfiguration(HashMap<String, String> executorsConfigurationMap) {
        this.executorsConfiguration.putAll(executorsConfigurationMap);
    }
}
