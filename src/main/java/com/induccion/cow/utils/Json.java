package com.induccion.cow.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import spark.Request;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

public enum Json {
    INSTANCE;

    public final ObjectMapper mapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public Map<String, Object> requestToMap(Request request) throws Exception {
        String body = URLDecoder.decode(request.body(), StandardCharsets.UTF_8.name());

        if(body.length() == 0) {
            return new TreeMap<>();
        }

        if(!request.contentType().equals(Constants.TYPE_JSON)) {
            return mapParameterFromBody(body);
        }
        // Porque necesite esto ¿¿¿??
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return mapper.readValue(body, new TypeReference<Map<String, Object>>() {});
    }

    private Map<String, Object> mapParameterFromBody(String body) {
        Map<String, Object> mapResult = new TreeMap<>();
        String[] parameterSplit = body.split("&");
        for(int i = 0; i < parameterSplit.length; i++) {
            String[] keyValueSplit = parameterSplit[i].split("=");
            mapResult.put(keyValueSplit[0], keyValueSplit[1]);
        }
        return mapResult;
    }
}
