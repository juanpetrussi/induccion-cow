package com.induccion.cow.models;

import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Identification;
import com.mercadopago.resources.datastructures.payment.Payer;

public class PaymentDto {

    private String payerFirstName;
    private String payerLastName;
    private String payerEmail;

    private String payerIdentificationType;
    private String payerIdentificationNumber;

    private Float paymentAmount;
    private String paymentDescription;
    private String paymentToken;
    private Integer paymentInstallments;
    private String paymentPaymentMethodId;

    public String getPayerFirstName() {
        return payerFirstName;
    }

    public void setPayerFirstName(String payerFirstName) {
        this.payerFirstName = payerFirstName;
    }

    public String getPayerLastName() {
        return payerLastName;
    }

    public void setPayerLastName(String payerLastName) {
        this.payerLastName = payerLastName;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPayerIdentificationType() {
        return payerIdentificationType;
    }

    public void setPayerIdentificationType(String payerIdentificationType) {
        this.payerIdentificationType = payerIdentificationType;
    }

    public String getPayerIdentificationNumber() {
        return payerIdentificationNumber;
    }

    public void setPayerIdentificationNumber(String payerIdentificationNumber) {
        this.payerIdentificationNumber = payerIdentificationNumber;
    }

    public Float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public String getPaymentToken() {
        return paymentToken;
    }

    public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
    }

    public Integer getPaymentInstallments() {
        return paymentInstallments;
    }

    public void setPaymentInstallments(Integer paymentInstallments) {
        this.paymentInstallments = paymentInstallments;
    }

    public String getPaymentPaymentMethodId() {
        return paymentPaymentMethodId;
    }

    public void setPaymentPaymentMethodId(String paymentPaymentMethodId) {
        this.paymentPaymentMethodId = paymentPaymentMethodId;
    }

    public Payer getPayer() {
        return new Payer().setFirstName(getPayerFirstName())
                .setLastName(getPayerLastName())
                .setEmail(getPayerEmail())
                .setIdentification(new Identification()
                        .setType(getPayerIdentificationType())
                        .setNumber(getPayerIdentificationNumber()));
    }

    public Payment getPayment() {
        return new Payment().setTransactionAmount(getPaymentAmount())
                .setDescription(getPaymentDescription())
                .setToken(getPaymentToken())
                .setInstallments(getPaymentInstallments())
                .setPaymentMethodId(getPaymentPaymentMethodId());
    }
}
