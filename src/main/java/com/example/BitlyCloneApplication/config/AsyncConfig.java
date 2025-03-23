package com.example.BitlyCloneApplication.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="server.tomcat.threads")
public class AsyncConfig {
private Long max;

    public Long getMax() {
        return max;
    }
    public void setMax(Long max) {
        this.max = max;
    }
}
