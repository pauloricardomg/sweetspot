package br.com.chaordic.sweetspot.core.configuration;

import org.hibernate.validator.constraints.NotEmpty;

public class AWSConfiguration {

    private static final Integer DEFAULT_MAXTHREADS = 50;

    @NotEmpty
    private String accessKey;

    @NotEmpty
    private String secretKey;

    @NotEmpty
    private String sshPublicKeyFile;

    private Integer maxExecutorThreads = DEFAULT_MAXTHREADS;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Integer getMaxExecutorThreads() {
        return maxExecutorThreads;
    }

    public void setMaxExecutorThreads(Integer maxExecutorThreads) {
        this.maxExecutorThreads = maxExecutorThreads;
    }

    public String getSshPublicKeyFile() {
        return sshPublicKeyFile;
    }

    public void setSshPublicKeyFile(String sshPublicKeyFile) {
        this.sshPublicKeyFile = sshPublicKeyFile;
    }
}
