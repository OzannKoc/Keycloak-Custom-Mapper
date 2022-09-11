package com.user.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ObjectMapperUtil {

    private ObjectMapperUtil() {
    }

    public static <T> T objectMapper(Object data, Class<T> tClass){
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return objectMapper.convertValue(data, tClass);
    }
}
