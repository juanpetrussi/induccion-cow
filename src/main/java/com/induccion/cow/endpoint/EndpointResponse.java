package com.induccion.cow.endpoint;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EndpointResponse {

    @JsonProperty("status")
    private int codeStatus;
    @JsonProperty("description")
    private String description;

    public EndpointResponse() {
    }

    public EndpointResponse(int codeError, String descriptionError) {
        this.codeStatus = codeError;
        this.description = descriptionError;
    }

    public int getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(int codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
