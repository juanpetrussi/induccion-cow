package com.induccion.cow.converters;

import com.induccion.cow.models.PreferenceDto;
import com.induccion.cow.utils.Constants;
import com.induccion.cow.utils.RequestUtils;
import spark.Request;

public class PreferenceConverter {

    public static PreferenceDto convert(Request request) {
        PreferenceDto preferenceDto = new PreferenceDto();

        preferenceDto.setItemId(RequestUtils.getBodyParameter(request, Constants.ID_PARAM).toString());
        preferenceDto.setItemTitle(RequestUtils.getBodyParameter(request, Constants.TITLE_PARAM));
        preferenceDto.setItemQuantity(Integer.valueOf(RequestUtils.getBodyParameter(request, Constants.QUANTITY_PARAM).toString()));
        preferenceDto.setItemCurrencyId(RequestUtils.getBodyParameter(request, Constants.CURRENCY_PARAM));
        preferenceDto.setItemUnitPrice(Float.valueOf(RequestUtils.getBodyParameter(request, Constants.PRICE_PARAM).toString()));
        preferenceDto.setPayerName(RequestUtils.getBodyParameter(request, Constants.NAME_PARAM));
        preferenceDto.setPayerSurname(RequestUtils.getBodyParameter(request, Constants.SURNAME_PARAM));
        preferenceDto.setPayerEmail(RequestUtils.getBodyParameter(request, Constants.EMAIL_PARAM));
        preferenceDto.setPayerDateCreated(RequestUtils.getBodyParameter(request, Constants.DATE_CREATED_PARAM));
        preferenceDto.setIdentificationType(RequestUtils.getBodyParameter(request, Constants.IDENTIFICATION_TYPE_PARAM));
        preferenceDto.setIdentificationNumber(RequestUtils.getBodyParameter(request, Constants.IDENTIFICATION_NUMBER_PARAM));

        return preferenceDto;
    }
}
