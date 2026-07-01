package com.pokeadmin.adminteampokemon.common.exception;

import org.springframework.http.HttpStatus;

public abstract class BusinessException extends RuntimeException {

    private final HttpStatus status;

    protected BusinessException(String message,
                                HttpStatus status) {
        super(message);
        this.status = status;
    }

    protected BusinessException(String message) {
        super(message);
        this.status = HttpStatus.CONFLICT;
    }

    public HttpStatus getStatus() {
        return status;
    }
}