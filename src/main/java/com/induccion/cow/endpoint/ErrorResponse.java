package com.induccion.cow.endpoint;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

    private final String message;
    private final String error;
    @JsonProperty("status")
    private final int httpStatusCode;

    public ErrorResponse(String message, String error, int httpStatusCode) {
        this.message = message;
        this.error = error;
        this.httpStatusCode = httpStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
