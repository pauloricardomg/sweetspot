package br.com.chaordic.sweetspot.health;

import br.com.chaordic.sweetspot.core.manager.AWSManager;

import com.codahale.metrics.health.HealthCheck;

public class AWSHealthCheck extends HealthCheck {

    public AWSHealthCheck(AWSManager awsManager) {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected Result check() throws Exception {
        return Result.unhealthy("Cannot contact AWS server.");
    }

}
