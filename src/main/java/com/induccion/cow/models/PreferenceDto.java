package com.induccion.cow.models;

import com.mercadopago.resources.datastructures.preference.Identification;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;

public class PreferenceDto {

    private String itemId;
    private String itemTitle;
    private Integer itemQuantity;
    private String itemCurrencyId;
    private Float itemUnitPrice;

    private String payerName;
    private String payerSurname;
    private String payerEmail;
    private String payerDateCreated;

    private String identificationType;
    private String identificationNumber;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemCurrencyId() {
        return itemCurrencyId;
    }

    public void setItemCurrencyId(String itemCurrencyId) {
        this.itemCurrencyId = itemCurrencyId;
    }

    public Float getItemUnitPrice() {
        return itemUnitPrice;
    }

    public void setItemUnitPrice(Float itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerSurname() {
        return payerSurname;
    }

    public void setPayerSurname(String payerSurname) {
        this.payerSurname = payerSurname;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPayerDateCreated() {
        return payerDateCreated;
    }

    public void setPayerDateCreated(String payerDateCreated) {
        this.payerDateCreated = payerDateCreated;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public Item getItem() {
        return new Item().setId(this.getItemId())
                .setTitle(this.getItemTitle())
                .setQuantity(this.getItemQuantity())
                .setCurrencyId(this.getItemCurrencyId())
                .setUnitPrice(this.getItemUnitPrice());
    }

    public Payer getPayer() {
        return new Payer().setName(this.getPayerName())
                .setSurname(this.getPayerSurname())
                .setEmail(this.getPayerEmail())
                .setDateCreated(this.getPayerDateCreated())
                .setIdentification(new Identification()
                        .setType(this.getIdentificationType())
                        .setNumber(this.getIdentificationNumber()));
    }
}
