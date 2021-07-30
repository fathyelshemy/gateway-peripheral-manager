package com.musala.gateways.manager.ut.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestingUtil {

    public static String ObjectMapperToString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper= new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter objectWriter= objectMapper.writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(obj);

    }
}
