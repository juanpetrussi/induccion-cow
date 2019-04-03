package com.induccion.cow.converters;

import com.induccion.cow.models.PaymentDto;
import com.induccion.cow.utils.Constants;
import com.induccion.cow.utils.RequestUtils;
import spark.Request;

public class PaymentConverter {

    public static PaymentDto convert(Request request) {
        PaymentDto paymentDto = new PaymentDto();

        paymentDto.setPayerFirstName(RequestUtils.getBodyParameter(request, Constants.NAME_PARAM));
        paymentDto.setPayerLastName(RequestUtils.getBodyParameter(request, Constants.SURNAME_PARAM));
        paymentDto.setPayerEmail(RequestUtils.getBodyParameter(request, Constants.EMAIL_PARAM));
        paymentDto.setPayerIdentificationType(RequestUtils.getBodyParameter(request, Constants.IDENTIFICATION_TYPE_PARAM));
        paymentDto.setPayerIdentificationNumber(RequestUtils.getBodyParameter(request, Constants.IDENTIFICATION_NUMBER_PARAM));

        paymentDto.setPaymentAmount(Float.parseFloat(RequestUtils.getBodyParameter(request, Constants.AMOUNT_PARAM)));
        paymentDto.setPaymentDescription(RequestUtils.getBodyParameter(request, Constants.DESCRIPTION_PARAM));
        paymentDto.setPaymentToken(RequestUtils.getBodyParameter(request, Constants.TOKEN_PARAM));
        paymentDto.setPaymentInstallments(Integer.parseInt(RequestUtils.getBodyParameter(request, Constants.INSTALLMENT_PARAM)));
        paymentDto.setPaymentPaymentMethodId(RequestUtils.getBodyParameter(request, Constants.PAYMENT_METHOD_ID_PARAM));

        return paymentDto;
    }
}
