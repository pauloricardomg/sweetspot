package br.com.chaordic.sweetspot;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import br.com.chaordic.sweetspot.core.manager.AWSManager;
import br.com.chaordic.sweetspot.core.manager.ChefManager;
import br.com.chaordic.sweetspot.core.manager.RequestManager;
import br.com.chaordic.sweetspot.health.AWSHealthCheck;
import br.com.chaordic.sweetspot.health.ChefHealthCheck;
import br.com.chaordic.sweetspot.resources.RequestsResource;

import com.codahale.metrics.servlets.MetricsServlet;

public class SweetSpotApplication extends Application<SweetSpotConfiguration> {
    public static void main(String[] args) throws Exception {
        new SweetSpotApplication().run(args);
    }

    @Override
    public String getName() {
        return "sweetspot";
    }

    @Override
    public void initialize(Bootstrap<SweetSpotConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(SweetSpotConfiguration conf, Environment env) {
        /* Managers */
        AWSManager awsManager = new AWSManager(env.lifecycle(), conf.getAwsConfiguration());
        ChefManager chefManager = new ChefManager(env.lifecycle(), conf.getChefConfiguration());
        RequestManager requestManager = new RequestManager(awsManager, conf.getMaxBids());

        /* Health Checks */
        env.healthChecks().register("aws", new AWSHealthCheck(awsManager));
        env.healthChecks().register("chef", new ChefHealthCheck(chefManager));

        /* Servlets */
        env.servlets().addServlet("metrics-servlet", new MetricsServlet(env.metrics())).addMapping("/metrics");

        /* Resources */
        env.jersey().register(new RequestsResource(requestManager));
    }
}
