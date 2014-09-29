package br.com.chaordic.sweetspot.health;

import br.com.chaordic.sweetspot.core.manager.ChefManager;

import com.codahale.metrics.health.HealthCheck;

public class ChefHealthCheck extends HealthCheck {

    public ChefHealthCheck(ChefManager chefManager) {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }

}
