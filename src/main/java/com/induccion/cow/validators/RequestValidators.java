package com.induccion.cow.validators;

import com.induccion.cow.utils.Constants;
import com.induccion.cow.utils.RequestUtils;
import org.apache.http.HttpStatus;
import spark.Request;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RequestValidators {

    private static void validateRequest(Request request, Set<String> parametersList) {
        Map<String, Object> paramsMap = RequestUtils.getBodyMapParameter(request);
        if(paramsMap == null) {
            throw new IllegalArgumentException();
        }

        for(String parameter : parametersList) {
            Object parameterResult = RequestUtils.getBodyParameter(paramsMap, parameter);
            if(parameterResult == null) {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void validatePreferenceRequest(Request request) {
        Set<String> parametersList = new HashSet<>(Arrays.asList(Constants.ID_PARAM, Constants.TITLE_PARAM, Constants.QUANTITY_PARAM,
                Constants.CURRENCY_PARAM, Constants.PRICE_PARAM, Constants.NAME_PARAM, Constants.SURNAME_PARAM, Constants.EMAIL_PARAM,
                Constants.DATE_CREATED_PARAM, Constants.IDENTIFICATION_TYPE_PARAM, Constants.IDENTIFICATION_NUMBER_PARAM));

        validateRequest(request, parametersList);
    }

    public static void validatePayWebCheckouRequest(Request request) {
        Set<String> parametersList = new HashSet<>(Arrays.asList(Constants.AMOUNT_PARAM, Constants.DESCRIPTION_PARAM, Constants.TOKEN_PARAM,
                Constants.INSTALLMENT_PARAM, Constants.PAYMENT_METHOD_ID_PARAM));

        validateRequest(request, parametersList);
    }
}
