package com.hieuminh.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ObjectMapperResolve {

    private Logger logger = Logger.getLogger(ObjectMapperResolve.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    private ObjectMapperResolve() {
    }

    public <T> T readValue(String content, Class<T> type) {
        try {
            return objectMapper.readValue(content, type);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String writeValueAsString (Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
