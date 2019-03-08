package com.induccion.cow.Endpoint;

import com.induccion.cow.Utils.EndpointUtils;
import com.induccion.cow.Utils.JsonUtils;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.*;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.servlet.SparkApplication;

public class RestEndpoint implements SparkApplication {


    @Override
    public void init() {
        Spark.after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        Spark.post(EndpointUtils.PATH_PUNTO_1, RestEndpoint::createPreferencePunto1, JsonUtils.json());

        Spark.post(EndpointUtils.PATH_PUNTO_3, RestEndpoint::recibirPagoPunto3, JsonUtils.json());

        Spark.post(EndpointUtils.PATH_PUNTO_4, RestEndpoint::tokenizerPunto4, JsonUtils.json());

        Spark.post(EndpointUtils.PATH_PUNTO_5, RestEndpoint::procesarPagoPunto5, JsonUtils.json());
    }

    private static Object createPreferencePunto1(Request request, Response response) throws MPException {
        MercadoPago.SDK.setClientId(EndpointUtils.ENV_CLIENT_ID);
        MercadoPago.SDK.setClientSecret(EndpointUtils.ENV_CLIENT_SECRET);

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

        BackUrls backUrls = new BackUrls(
                "https://www.tu-sitio/success",
                "http://www.tu-sitio/pending",
                "http://www.tu-sitio/failure");

        return new Preference().setPayer(payer).appendItem(item).setBackUrls(backUrls).save();
    }

    private static Object recibirPagoPunto3(Request request, Response response) throws MPException {
        MercadoPago.SDK.setAccessToken(EndpointUtils.ACCESS_TOKEN);

        com.mercadopago.resources.datastructures.payment.Payer payer = new com.mercadopago.resources.datastructures.payment.Payer();
        payer.setFirstName("Juan")
                .setLastName("Perez")
                .setEmail(request.queryParams("email"))
                .setIdentification(new com.mercadopago.resources.datastructures.payment.Identification()
                        .setType("DNI")
                        .setNumber("12345678"));

        Payment payment = new Payment();
        payment.setTransactionAmount(Float.parseFloat(request.queryParams("amount")))
                .setDescription("Pago Punto 3")
                .setToken(request.queryParams("token"))
                .setInstallments(Integer.valueOf(request.queryParams("installment")))
                .setPaymentMethodId(request.queryParams("paymentMethodId"))
                .setPayer(payer);

        payment.save();

        return payment;
    }

    private static Object tokenizerPunto4(Request request, Response response) throws MPException {
        MercadoPago.SDK.setAccessToken(EndpointUtils.ACCESS_TOKEN);

        com.mercadopago.resources.datastructures.payment.Payer payer = new com.mercadopago.resources.datastructures.payment.Payer()
                .setEmail("test53727@test.com");

        Payment payment = new Payment();
        payment.setTransactionAmount(456F)
                .setDescription("Descripcion Tokenizer")
                .setToken(request.queryParams("token"))
                .setPaymentMethodId(request.queryParams("payment_method_id"))
                .setInstallments(Integer.valueOf(request.queryParams("installments")))
                .setIssuerId(request.queryParams("issuer_id"))
                .setPayer(payer);

        payment.save();

        return payment;
    }

    private static Object procesarPagoPunto5(Request request, Response response) throws MPException {
        return "RESPONSE PUNTO 5";
    }
}
