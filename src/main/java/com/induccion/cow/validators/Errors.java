package com.induccion.cow.validators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Errors {

    private List<String> errorsList;

    public Errors() {
        errorsList = new ArrayList<>();
    }

    public Errors (Collection<String> errorsExist) {
        errorsExist = new ArrayList<>(errorsExist);
    }

    public List<String> getErrorsList() {
        return errorsList;
    }

    public void addError(String error) {
        errorsList.add(error);
    }

    public boolean hasErrors() {
        return !errorsList.isEmpty();
    }
}
