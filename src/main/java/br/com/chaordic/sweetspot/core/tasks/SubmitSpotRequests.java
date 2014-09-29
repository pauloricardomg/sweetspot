package br.com.chaordic.sweetspot.core.tasks;

import br.com.chaordic.sweetspot.core.manager.AWSManager;
import br.com.chaordic.sweetspot.core.pojo.SweetSpotRequest;


public class SubmitSpotRequests implements Runnable {

    private AWSManager awsManger;
    private SweetSpotRequest request;

    public SubmitSpotRequests(AWSManager awsManager, SweetSpotRequest request) {
        this.awsManger = awsManager;
        this.request = request;
    }

    @Override
    public void run() {
        awsManger.submitSpotInstanceRequest(request);
    }

}
