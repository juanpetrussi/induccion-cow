package com.induccion.cow.validators;

import com.induccion.cow.utils.Constants;
import com.induccion.cow.utils.ErrorMessages;
import com.induccion.cow.utils.RequestUtils;
import spark.Request;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RequestValidators {

    /* Mejorar validaciones */

    private static Errors validateRequest(Map<String, Object> paramsMap, Set<String> parametersList) {
        Errors errors = new Errors();

        //Map<String, Object> paramsMap = RequestUtils.getBodyMapParameter(request);
        if(paramsMap == null) {
            errors.addError(ErrorMessages.REQUEST_FORMAT_ERROR_MESSAGE);
            return errors;
        }

        parametersList.forEach(cadaParemeter -> {
            Object parameterResult = RequestUtils.getBodyParameter(paramsMap, cadaParemeter);
            if(parameterResult == null) {
                errors.addError(String.format(ErrorMessages.REQUEST_PARAM_ERROR_MESSAGE, cadaParemeter));
            }
        });

        return errors;
    }

    public static Errors validatePreferenceRequest(Request request) {
        Set<String> parametersList = new HashSet<>(Arrays.asList(Constants.ID_PARAM, Constants.TITLE_PARAM, Constants.QUANTITY_PARAM,
                Constants.CURRENCY_PARAM, Constants.PRICE_PARAM, Constants.NAME_PARAM, Constants.SURNAME_PARAM, Constants.EMAIL_PARAM,
                Constants.DATE_CREATED_PARAM, Constants.IDENTIFICATION_TYPE_PARAM, Constants.IDENTIFICATION_NUMBER_PARAM));

        Map<String, Object> paramsMap = RequestUtils.getBodyMapParameter(request);

        Errors errorsList = validateRequest(paramsMap, parametersList);
        if(!errorsList.hasErrors()) {
            if(!ValidatorsUtils.isIntegerPositive(paramsMap.get(Constants.ID_PARAM).toString())){
                errorsList.addError(String.format(ErrorMessages.REQUEST_PARAM_INTEGER_ERROR, Constants.ID_PARAM));
            }
            if(!ValidatorsUtils.isIntegerPositive(paramsMap.get(Constants.QUANTITY_PARAM).toString())){
                errorsList.addError(String.format(ErrorMessages.REQUEST_PARAM_INTEGER_ERROR, Constants.QUANTITY_PARAM));
            }
            if(!ValidatorsUtils.isDecimalPositive(paramsMap.get(Constants.PRICE_PARAM).toString())){
                errorsList.addError(String.format(ErrorMessages.REQUEST_PARAM_DECIMAL_ERROR, Constants.PRICE_PARAM));
            }
            if(!ValidatorsUtils.isStringValid(paramsMap.get(Constants.TITLE_PARAM).toString())){
                errorsList.addError(String.format(ErrorMessages.REQUEST_PARAM_STRING_ERROR, Constants.TITLE_PARAM));
            }
            if(!ValidatorsUtils.isStringValid(paramsMap.get(Constants.NAME_PARAM).toString())){
                errorsList.addError(String.format(ErrorMessages.REQUEST_PARAM_STRING_ERROR, Constants.NAME_PARAM));
            }
            if(!ValidatorsUtils.isStringValid(paramsMap.get(Constants.SURNAME_PARAM).toString())){
                errorsList.addError(String.format(ErrorMessages.REQUEST_PARAM_STRING_ERROR, Constants.SURNAME_PARAM));
            }
            if(!ValidatorsUtils.isStringValid(paramsMap.get(Constants.EMAIL_PARAM).toString())){
                errorsList.addError(String.format(ErrorMessages.REQUEST_PARAM_STRING_ERROR, Constants.EMAIL_PARAM));
            }
            if(!ValidatorsUtils.isStringValid(paramsMap.get(Constants.DATE_CREATED_PARAM).toString())){
                errorsList.addError(String.format(ErrorMessages.REQUEST_PARAM_STRING_ERROR, Constants.DATE_CREATED_PARAM));
            }
            if(!ValidatorsUtils.isStringValid(paramsMap.get(Constants.IDENTIFICATION_TYPE_PARAM).toString())){
                errorsList.addError(String.format(ErrorMessages.REQUEST_PARAM_STRING_ERROR, Constants.IDENTIFICATION_TYPE_PARAM));
            }
            if(!ValidatorsUtils.isStringValid(paramsMap.get(Constants.IDENTIFICATION_NUMBER_PARAM).toString())){
                errorsList.addError(String.format(ErrorMessages.REQUEST_PARAM_STRING_ERROR, Constants.IDENTIFICATION_NUMBER_PARAM));
            }
        }

        return errorsList;
    }

    public static Errors validatePayWebCheckouRequest(Request request) {
        Set<String> parametersList = new HashSet<>(Arrays.asList(Constants.AMOUNT_PARAM, Constants.DESCRIPTION_PARAM, Constants.TOKEN_PARAM,
                Constants.INSTALLMENT_PARAM, Constants.PAYMENT_METHOD_ID_PARAM));

        Map<String, Object> paramsMap = RequestUtils.getBodyMapParameter(request);

        return validateRequest(paramsMap, parametersList);
    }
}
