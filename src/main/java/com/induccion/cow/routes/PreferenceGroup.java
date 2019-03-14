package com.induccion.cow.routes;

import com.induccion.cow.endpoint.EndpointResponse;
import com.induccion.cow.services.PreferenceService;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Identification;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.RouteGroup;

public class PreferenceGroup implements RouteGroup{
    @Override
    public void addRoutes() {
        // TODO Definir comportamiento
    }

    public static Object createPreferencePunto1(Request request, Response response) throws MPException {
        PreferenceService preferenceService = new PreferenceService();
        return preferenceService.createPreferencePunto1(request, response);
    }

    public static Object createPreferencePunto1Front(Request request, Response response) throws MPException {
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

        Preference pref = new Preference().setPayer(payer).appendItem(item).save();

        return new EndpointResponse(HttpStatus.SC_CREATED, pref.getInitPoint());
    }
}
