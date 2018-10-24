package com.coding.challenge.api;

public class AlreadyExistingError extends ApiError{

    private String[] errorParams;

    public AlreadyExistingError(String message, String errorCode, String...params) {
        super(message, errorCode);
        this.errorParams = params;
    }

    public String[] getErrorParams() {
        return errorParams;
    }
}
