package com.induccion.cow.services;

import com.induccion.cow.endpoint.EndpointResponse;
import com.induccion.cow.utils.Constants;
import com.induccion.cow.utils.RequestUtils;
import com.induccion.cow.validators.RequestValidators;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Identification;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

public class PreferenceService {

    public Object createPreferencePunto1(Request request, Response response) throws MPException {
        // Valido el formato del JSON enviado en el body como request
        // Valido que existan todos los parametros necesarios
        RequestValidators.validatePreferenceRequest(request);

        Item item = new Item().setId(RequestUtils.getBodyParameter(request, Constants.ID_PARAM).toString())
                .setTitle(RequestUtils.getBodyParameter(request, Constants.TITLE_PARAM))
                .setQuantity(Integer.valueOf(RequestUtils.getBodyParameter(request, Constants.QUANTITY_PARAM).toString()))
                .setCurrencyId(RequestUtils.getBodyParameter(request, Constants.CURRENCY_PARAM))
                .setUnitPrice(Float.valueOf(RequestUtils.getBodyParameter(request, Constants.PRICE_PARAM).toString()));

        Payer payer = new Payer();
        payer.setName(RequestUtils.getBodyParameter(request, Constants.NAME_PARAM))
                .setSurname(RequestUtils.getBodyParameter(request, Constants.SURNAME_PARAM))
                .setEmail(RequestUtils.getBodyParameter(request, Constants.EMAIL_PARAM))
                .setDateCreated(RequestUtils.getBodyParameter(request, Constants.DATE_CREATED_PARAM))
                .setIdentification(new Identification()
                        .setType(RequestUtils.getBodyParameter(request, Constants.IDENTIFICATION_TYPE_PARAM))
                        .setNumber(RequestUtils.getBodyParameter(request, Constants.IDENTIFICATION_NUMBER_PARAM)));

        Preference preference = new Preference().setPayer(payer).appendItem(item).save();

        response.status(HttpStatus.SC_CREATED);
        response.body("InitPoint preference: " + preference.getInitPoint());

        return new EndpointResponse(response.status(), response.body());
    }
}
