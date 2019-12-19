package com.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DbConfig {
    @Value("${driverName}")
    private String driverName;

    @Value("${dbUrl}")
    private String dbUrl;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Value("${hbmToDdl}")
    private String hbmToDdl;

    @Value("${hibernateDilact}")
    private String hibernateDilact;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHbmToDdl() {
        return hbmToDdl;
    }

    public void setHbmToDdl(String hbmToDdl) {
        this.hbmToDdl = hbmToDdl;
    }

    public String getHibernateDilact() {
        return hibernateDilact;
    }

    public void setHibernateDilact(String hibernateDilact) {
        this.hibernateDilact = hibernateDilact;
    }
}
