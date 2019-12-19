package com.test.responseDTO;

public class UserRegisterDto {
    private int noOfrowParsed;
    private int noOfrowFailed;
    private int noOfUserCreated;
    private String errorFileUrl;

    public int getNoOfrowParsed() {
        return noOfrowParsed;
    }

    public void setNoOfrowParsed(int noOfrowParsed) {
        this.noOfrowParsed = noOfrowParsed;
    }

    public int getNoOfrowFailed() {
        return noOfrowFailed;
    }

    public void setNoOfrowFailed(int noOfrowFailed) {
        this.noOfrowFailed = noOfrowFailed;
    }

    public int getNoOfUserCreated() {
        return noOfUserCreated;
    }

    public void setNoOfUserCreated(int noOfUserCreated) {
        this.noOfUserCreated = noOfUserCreated;
    }

    public String getErrorFileUrl() {
        return errorFileUrl;
    }

    public void setErrorFileUrl(String errorFileUrl) {
        this.errorFileUrl = errorFileUrl;
    }
}
