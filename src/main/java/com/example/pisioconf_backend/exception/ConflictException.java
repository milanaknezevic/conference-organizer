package com.example.pisioconf_backend.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends HttpException {

    public ConflictException() {
        super(HttpStatus.CONFLICT, null);
    }


    public ConflictException(Object data) {
        super(HttpStatus.CONFLICT, data);
    }
}