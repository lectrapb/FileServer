package com.app.back.config;

public class BusinessException extends RuntimeException {

    public BusinessException(){}

    public BusinessException(String message) {
        super(message);
    }


}
