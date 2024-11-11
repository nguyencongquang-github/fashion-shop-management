package com.example.fashion_shop_management.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AppException extends RuntimeException{
    ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}