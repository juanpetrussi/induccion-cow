package com.induccion.cow.controllers;

import com.induccion.cow.converters.PreferenceConverter;
import com.induccion.cow.endpoint.SuccessResponse;
import com.induccion.cow.models.PreferenceDto;
import com.induccion.cow.services.PreferenceService;
import com.induccion.cow.utils.Constants;
import com.induccion.cow.utils.RequestValidateException;
import com.induccion.cow.validators.Errors;
import com.induccion.cow.validators.RequestValidators;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Identification;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

public class PreferenceController {

    public static SuccessResponse createPreferencePunto1(Request request, Response response) throws MPException, RequestValidateException {
        Errors errors = RequestValidators.validatePreferenceRequest(request);
        if(errors.hasErrors()){
            throw new RequestValidateException(errors.getErrorsList());
        }

        PreferenceDto preferenceDto = PreferenceConverter.convert(request);

        Preference preferenceSaved = PreferenceService.getInstance().createPreferencePunto1(preferenceDto);
        return new SuccessResponse(HttpStatus.SC_CREATED, Constants.PREFERENCE_SUCCESS_MESSAGE, preferenceSaved.getInitPoint());
    }

    public static SuccessResponse createPreferenceFront(Request request, Response response) throws MPException {
        Item item = new Item().setId("123").setTitle("Item prueba Induccion COW").setQuantity(1)
                .setCurrencyId("ARS").setUnitPrice((float) 65.33);

        Payer payer = new Payer();
        payer.setName("Juan")
                .setSurname("Perez")
                .setEmail("test_user_83179288@testuser.com")
                .setDateCreated("2018-06-02T12:58:41.425-04:00")
                .setIdentification(new Identification()
                        .setType("DNI")
                        .setNumber("12345678"));

        Preference preferenceSaved = new Preference().setPayer(payer).appendItem(item).save();

        /*Map<String, Object> modelVelocity = new HashMap<>();
        modelVelocity.put("initPoint", pref.getInitPoint());
        return VelocityUtils.getTemplateEngine(modelVelocity, Constants.PATH_TO_TEMPLATE_STEP_2);*/

        return new SuccessResponse(HttpStatus.SC_CREATED, Constants.PREFERENCE_SUCCESS_MESSAGE, preferenceSaved.getInitPoint());
    }

}
