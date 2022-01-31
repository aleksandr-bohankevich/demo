package com.example.demo.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtils {

    public static <T> T unmarshal(String string, Class<T> clazz) throws Exception {
        return new ObjectMapper().readValue(string, clazz);
    }

    public static String marshal(Object object) throws Exception {
        return new ObjectMapper().writeValueAsString(object);
    }

    public static boolean isJSONValid(String jsonInString ) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static String beautify(String json) throws IOException {
        if (!isJSONValid(json)) {
            return json;
        }
        final ObjectMapper mapper = new ObjectMapper();
        Object obj = mapper.readValue(json, Object.class);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }

}
