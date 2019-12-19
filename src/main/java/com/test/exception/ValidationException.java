package com.test.exception;

public class ValidationException extends Exception {
    private int code;
    private String message;

    public ValidationException(int code,String msg){
        this.code=code;
        this.message = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
