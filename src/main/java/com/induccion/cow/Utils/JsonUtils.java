package com.induccion.cow.Utils;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonUtils {
        public static String toJson(Object object) {
            return new Gson().toJson(object);
        }

        public static ResponseTransformer json() {
            return JsonUtils::toJson;
        }
}
