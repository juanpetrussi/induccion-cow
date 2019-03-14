package com.induccion.cow.routes;

import com.induccion.cow.services.PaymentService;
import com.induccion.cow.services.PreferenceService;
import com.mercadopago.exceptions.MPException;
import com.sun.xml.internal.bind.v2.TODO;
import spark.Request;
import spark.Response;
import spark.RouteGroup;

public class PaymentGroup implements RouteGroup{
    @Override
    public void addRoutes() {
        // TODO Definir comportamiento
    }

    public static Object recibirPagoPunto3(Request request, Response response) throws MPException {
        PaymentService paymentService = new PaymentService();
        return paymentService.createPayWebCheckout(request, response);
    }
}
