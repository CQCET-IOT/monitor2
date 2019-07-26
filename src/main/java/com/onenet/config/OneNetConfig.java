package com.onenet.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 中国移动OneNET平台基本配置
 * Created by thinker on 2019/5/2.
 */
@ConfigurationProperties(prefix = "onenet")
@Component
public class OneNetConfig {
    // 产品APIKEY
    private String apiKey;
    // 设备ID
    private String deviceId;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @PostConstruct
    public void init() {
        System.out.println("读取配置文件");
        System.out.println("APIKEY: " + getApiKey());
        System.out.println("DeviceId: " + getDeviceId());
    }
}