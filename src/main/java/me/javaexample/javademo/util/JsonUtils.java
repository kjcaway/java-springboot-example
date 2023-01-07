package me.javaexample.javademo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    static final TypeReference<Map<String, Object>> typeOfMap = new TypeReference<>() {
    };

    public static <T> T convertToObject(String json, Class<T> type) throws Exception {
        try {
            return objectMapper.readValue(json, type);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public static <T> T convertToObject(String json, TypeReference<T> type) throws Exception {
        try {
            return objectMapper.readValue(json, type);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static String convertToJsonStr(Object obj) throws Exception {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static Map<String, Object> convertToMap(String json) throws Exception {
        try {
            return objectMapper.readValue(json, typeOfMap);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}