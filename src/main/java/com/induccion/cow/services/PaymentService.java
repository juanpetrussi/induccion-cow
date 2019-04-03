package com.induccion.cow.services;

import com.induccion.cow.models.PaymentDto;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;

public class PaymentService {

    /* Implementacion de Singleton pero sin Enum *//*
    private static final PaymentService INSTANCE = new PaymentService();
    private PaymentService() {
        if (INSTANCE != null) {
            throw new IllegalStateException("PaymentService singleton already created");
        }
    }
    public static PaymentService getInstance() {
        return  INSTANCE;
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone PaymentService class");
    }*/

    public Payment createPayWebCheckout(PaymentDto paymentDto) throws MPException {
        Payment payment = paymentDto.getPayment();
        payment.setPayer(paymentDto.getPayer());

        return payment.save();
    }
}
