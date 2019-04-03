package com.induccion.cow.utils;

public class ErrorMessages {
    public static final String INVALID_MIGRATION_UPDATE = "invalid migration configurations";
    public static final String INVALID_USER_UPDATE = "invalid user configurations";

    public static final String BAD_REQUEST = "bad_request";
    public static final String INTERNAL_ERROR = "internal_error";
    public static final String NOT_FOUND = "not_found";

    public static String REQUEST_FORMAT_ERROR_MESSAGE = "Request format error";
    public static String REQUEST_PARAM_ERROR_MESSAGE = "Parámetro '%s' requerido";
    public static String REQUEST_PARAM_INTEGER_ERROR = "Parámetro '%s' debe ser un valor entero positivo";
    public static String REQUEST_PARAM_DECIMAL_ERROR = "Parámetro '%s' debe ser un valor decimal positivo";
    public static String REQUEST_PARAM_STRING_ERROR = "Parámetro '%s' debe ser un texto válido";

    private ErrorMessages() {}
}
