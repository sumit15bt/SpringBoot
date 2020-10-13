package com.demoSecurity.demoSpringSecurity.dbBeans;

import java.io.Serializable;

public class UserDetails implements Serializable {
    private String user;
    private String domain;


    public UserDetails(String user, String domain) {
        this.user = user;
        this.domain = domain;
    }

    public String getUser() {
        return user;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "user='" + user + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
