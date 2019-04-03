package com.induccion.cow.utils;

import org.apache.http.HttpStatus;

import java.util.List;

public class RequestValidateException extends Exception {

    private int statusCodeError = HttpStatus.SC_BAD_REQUEST;
    private List<String> errorsList;

    public RequestValidateException(List<String> errorsList) {
        super(null, null);
        this.errorsList = errorsList;
    }

    public int getStatusCodeError() {
        return statusCodeError;
    }

    public List<String> getErrorsList() {
        return errorsList;
    }

    public String getListErrorsMessage() {
        String errorMessage = "";
        for(String cadaError : getErrorsList()) {
            errorMessage = errorMessage.concat(cadaError).concat(", ");
        }
        return errorMessage;
    }
}
