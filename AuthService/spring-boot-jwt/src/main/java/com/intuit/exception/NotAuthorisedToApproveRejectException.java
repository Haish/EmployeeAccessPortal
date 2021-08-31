package com.intuit.exception;

public class NotAuthorisedToApproveRejectException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotAuthorisedToApproveRejectException(String msg) {
        super(msg);
    }
}
