package com.induccion.cow.utils;

import spark.Request;

import java.util.Map;

public class RequestUtils {

    public RequestUtils() {}

    public static Map<String, Object> getBodyMapParameter(Request request) {
        try{
            return Json.INSTANCE.requestToMap(request);
        }catch (Exception e) {
            return null;
        }
    }

    public static <T> T getBodyParameter(Map<String, Object> parameterMap, String parameter) {
        try{
            return (T) parameterMap.get(parameter);
        }catch (Exception e) {
            return null;
        }
    }

    public static <T> T getBodyParameter(Request request, String parameter) {
        try{
            return (T) Json.INSTANCE.requestToMap(request).get(parameter);
        }catch (Exception e) {
            return null;
        }
    }
}
