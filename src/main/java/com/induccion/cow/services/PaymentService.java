package com.induccion.cow.services;

import com.induccion.cow.endpoint.EndpointResponse;
import com.induccion.cow.utils.Constants;
import com.induccion.cow.utils.RequestUtils;
import com.induccion.cow.validators.RequestValidators;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Identification;
import com.mercadopago.resources.datastructures.payment.Payer;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

public class PaymentService {

    public Object createPayWebCheckout(Request request, Response response) throws MPException {
        // Valido el formato del JSON enviado en el body como request
        // Valido que existan todos los parametros necesarios
        RequestValidators.validatePayWebCheckouRequest(request);

        Payer payer = new Payer();
        payer.setFirstName(RequestUtils.getBodyParameter(request, Constants.NAME_PARAM))
                .setLastName(RequestUtils.getBodyParameter(request, Constants.SURNAME_PARAM))
                .setEmail(RequestUtils.getBodyParameter(request, Constants.EMAIL_PARAM))
                .setIdentification(new Identification()
                        .setType(RequestUtils.getBodyParameter(request, Constants.IDENTIFICATION_TYPE_PARAM))
                        .setNumber(RequestUtils.getBodyParameter(request, Constants.IDENTIFICATION_NUMBER_PARAM)));

        Payment payment = new Payment();
        payment.setTransactionAmount(Float.parseFloat(RequestUtils.getBodyParameter(request, Constants.AMOUNT_PARAM)))
                .setDescription(RequestUtils.getBodyParameter(request, Constants.DESCRIPTION_PARAM))
                .setToken(RequestUtils.getBodyParameter(request, Constants.TOKEN_PARAM))
                .setInstallments(Integer.valueOf(RequestUtils.getBodyParameter(request, Constants.INSTALLMENT_PARAM)))
                .setPaymentMethodId(RequestUtils.getBodyParameter(request, Constants.PAYMENT_METHOD_ID_PARAM))
                .setPayer(payer);

        payment.save();

        response.status(HttpStatus.SC_CREATED);
        response.body("ID Pago creado: " + payment.getId() + " - Estado del pago: " + payment.getStatus().toString());

        return new EndpointResponse(response.status(), response.body());
    }
}
