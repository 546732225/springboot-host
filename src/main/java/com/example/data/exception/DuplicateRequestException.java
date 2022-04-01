package com.example.data.exception;

/**
 * 重复请求异常
 * @author seven
 */
public class DuplicateRequestException extends RuntimeException {


    public DuplicateRequestException(String message) {
        super(message);
    }

    public DuplicateRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateRequestException(Throwable cause) {
        super(cause);
    }

    protected DuplicateRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
