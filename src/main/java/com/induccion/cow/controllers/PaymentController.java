package com.induccion.cow.controllers;

import com.induccion.cow.converters.PaymentConverter;
import com.induccion.cow.endpoint.SuccessResponse;
import com.induccion.cow.models.PaymentDto;
import com.induccion.cow.services.PaymentService;
import com.induccion.cow.utils.Constants;
import com.induccion.cow.utils.RequestValidateException;
import com.induccion.cow.validators.Errors;
import com.induccion.cow.validators.RequestValidators;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

public class PaymentController {

    public static SuccessResponse recibirPagoPunto3(Request request, Response response) throws MPException, RequestValidateException {
        Errors errors = RequestValidators.validatePayWebCheckouRequest(request);
        if(errors.hasErrors()){
            throw new RequestValidateException(errors.getErrorsList());
        }

        PaymentDto paymentDto = PaymentConverter.convert(request);
        
        Payment paymentSeved = PaymentService.getInstance().createPayWebCheckout(paymentDto);
        return new SuccessResponse(HttpStatus.SC_CREATED, Constants.PAYMENT_SUCCESS_MESSAGE, paymentSeved.getId());
    }

}
