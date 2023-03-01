package org.example.qa.config;

import com.typesafe.config.Config;

public class RestfulBookerConfig {
    public String baseUrl;
    public String userName;
    public String password;

    public RestfulBookerConfig(Config config) {
        this.baseUrl = config.getString("restful-booker.baseurl");
        this.userName = config.getString("restful-booker.username");
        this.password = config.getString("restful-booker.password");
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
