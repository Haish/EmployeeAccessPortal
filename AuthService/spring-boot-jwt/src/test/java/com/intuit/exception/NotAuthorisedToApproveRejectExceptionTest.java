package com.intuit.exception;

public class NotAuthorisedToApproveRejectExceptionTest extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotAuthorisedToApproveRejectExceptionTest(String msg) {
        super(msg);
    }
}
