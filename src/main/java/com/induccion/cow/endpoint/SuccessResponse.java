package com.induccion.cow.endpoint;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SuccessResponse {

    @JsonProperty("status")
    private int codeStatus;
    @JsonProperty("description")
    private String description;
    @JsonProperty("value")
    private String value;

    public SuccessResponse() {
    }

    public SuccessResponse(int codeStatus, String description, String value) {
        this.codeStatus = codeStatus;
        this.description = description;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
