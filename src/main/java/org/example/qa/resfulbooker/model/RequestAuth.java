package org.example.qa.resfulbooker.model;

import org.example.qa.config.RestfulBookerConfig;

public class RequestAuth {
    private final String username;
    private final String password;

    public RequestAuth(RestfulBookerConfig config) {
        this.username = config.getUserName();
        this.password = config.getPassword();
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
