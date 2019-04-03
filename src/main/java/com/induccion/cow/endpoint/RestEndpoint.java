package com.induccion.cow.endpoint;

import com.google.common.net.MediaType;
import com.induccion.cow.controllers.PaymentController;
import com.induccion.cow.controllers.PreferenceController;
import com.induccion.cow.utils.*;
import com.induccion.cow.utils.Constants;
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

        Spark.post(EndpointUtils.PATH_PUNTO_1_FRONT, PreferenceController::createPreferenceFront, new JsonTransformer());
        Spark.post(EndpointUtils.PATH_PUNTO_1, PreferenceController::createPreferencePunto1, new JsonTransformer());
        Spark.post(EndpointUtils.PATH_PUNTO_3, PaymentController::recibirPagoPunto3, new JsonTransformer());

        /* VELOCITY */
        Spark.get(EndpointUtils.PATH_PUNTO_2, PreferenceController::createPreferenceFront);

        /* EXCEPTIONS */
        Spark.exception(MPException.class, RestEndpoint::mpExceptionhandler);
        Spark.exception(RequestValidateException.class, RestEndpoint::requestValidateExceptionHandler);
        Spark.exception(Exception.class, RestEndpoint::exceptionHandler);
    }

    private static void mpExceptionhandler(MPException mpException, Request request, Response response) {
        ErrorResponse errorResponse = new ErrorResponse(mpException.getMessage(), ErrorMessages.BAD_REQUEST, HttpStatus.SC_BAD_REQUEST);
        buildResponse(response, errorResponse);
    }

    private static void requestValidateExceptionHandler(RequestValidateException exception, Request request, Response response) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getListErrorsMessage(), ErrorMessages.BAD_REQUEST, exception.getStatusCodeError());
        buildResponse(response, errorResponse);
    }

    private static void exceptionHandler(Exception exception, Request request, Response response) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), ErrorMessages.INTERNAL_ERROR, HttpStatus.SC_INTERNAL_SERVER_ERROR);
        buildResponse(response, errorResponse);
    }

    private static void buildResponse(Response response, ErrorResponse errorResponse) {
        response.header(Constants.CONTENT_TYPE, MediaType.JSON_UTF_8.toString());
        String responseBody = Json.INSTANCE.toJsonString(errorResponse);
        response.body(responseBody);
        response.status(errorResponse.getHttpStatusCode());
    }
}
