package com.induccion.cow.endpoint;

import com.induccion.cow.routes.PaymentGroup;
import com.induccion.cow.routes.PreferenceGroup;
import com.induccion.cow.utils.Constants;
import com.induccion.cow.utils.EndpointUtils;
import com.induccion.cow.utils.JsonTransformer;
import com.mercadopago.exceptions.MPException;
import org.apache.http.HttpStatus;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.servlet.SparkApplication;

public class RestEndpoint implements SparkApplication {


    // Implementar CONSTANTS

    @Override
    public void init() {
        Spark.before((request, response) -> response.type(Constants.TYPE_JSON));

        Spark.after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });

        try {
            EndpointUtils.setUserCredentials();

            Spark.post(EndpointUtils.PATH_PUNTO_1_FRONT, PreferenceGroup::createPreferencePunto1Front, new JsonTransformer());

            Spark.post(EndpointUtils.PATH_PUNTO_1, PreferenceGroup::createPreferencePunto1, new JsonTransformer());

            Spark.post(EndpointUtils.PATH_PUNTO_3, PaymentGroup::recibirPagoPunto3, new JsonTransformer());

            // Spark.post(EndpointUtils.PATH_PUNTO_4, RestEndpoint::tokenizerPunto4, new JsonTransformer());

            // Spark.post(EndpointUtils.PATH_PUNTO_5, RestEndpoint::procesarPagoPunto5, new JsonTransformer());

            Spark.exception(MPException.class, RestEndpoint::mpExceptionhandler);

            Spark.exception(Exception.class, RestEndpoint::exceptionHandler);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Punto 2
    Obtener resultado del pago*/

    /*private static Object tokenizerPunto4(Request request, Response response) throws MPException {
        com.mercadopago.resources.datastructures.payment.Payer payer = new com.mercadopago.resources.datastructures.payment.Payer()
                .setEmail("test53727@test.com");

        // Validar datos

        Payment payment = new Payment();
        payment.setTransactionAmount(456F)
                .setDescription("Descripcion Tokenizer")
                .setToken(request.queryParams("token"))
                .setPaymentMethodId(request.queryParams("payment_method_id"))
                .setInstallments(Integer.valueOf(request.queryParams("installments")))
                .setIssuerId(request.queryParams("issuer_id"))
                .setPayer(payer);

        payment.save();

        // Devolver resultado del pago al usuario

        return payment;
    }*/

  /*  private static Object procesarPagoPunto5(Request request, Response response) throws MPException {
        //return tokenizerPunto4(request, response);
        return "RESPONSE PUNTO 5";
    }*/


    private static void mpExceptionhandler(MPException mpException, Request request, Response response) {
        response.status(mpException.getStatusCode());
        response.body(mpException.getMessage());
    }

    private static void exceptionHandler(Exception exception, Request request, Response response) {
        if(exception instanceof IllegalArgumentException) {
            response.status(HttpStatus.SC_BAD_REQUEST);
            response.body(String.format("Error message: Error en formato de request"));
            return;
        }
        response.body(String.format("Error message: '%s'", exception.getMessage()));
        response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
