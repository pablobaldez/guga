package com.pablo.sample.domain;

/**
 * @author Pablo
 * @since 17/07/2016
 */
public class User {
    public String name;
    public String email;

    public User(){}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
