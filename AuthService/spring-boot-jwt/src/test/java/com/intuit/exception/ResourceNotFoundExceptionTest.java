package com.intuit.exception;

public class ResourceNotFoundExceptionTest extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundExceptionTest(String msg) {
        super(msg);
    }
}
