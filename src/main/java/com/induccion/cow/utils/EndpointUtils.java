package com.induccion.cow.utils;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;

public class EndpointUtils {

    // User credentials
    public static String ENV_CLIENT_ID = "157169529854984";
    public static String ENV_CLIENT_SECRET = "sz26FBlwTzRhg4LyPWVYRB8JkSs8nnD6";
    public static String ACCESS_TOKEN = "TEST-157169529854984-030609-2273cdb13a2a05fcbad5f1e924c839fd-411997139";

    // Paths declarations
    public static final String PATH_PUNTO_1_FRONT = "/induccion/punto1/front";
    public static final String PATH_PUNTO_1 = "/induccion/punto1";
    public static final String PATH_PUNTO_3 = "/induccion/punto3";
    public static final String PATH_PUNTO_4 = "/induccion/punto4";
    public static final String PATH_PUNTO_5 = "/induccion/punto5";

    public static void setUserCredentials() throws MPException {
        MercadoPago.SDK.setClientId(EndpointUtils.ENV_CLIENT_ID);
        MercadoPago.SDK.setClientSecret(EndpointUtils.ENV_CLIENT_SECRET);
        MercadoPago.SDK.setAccessToken(EndpointUtils.ACCESS_TOKEN);
    }
}
