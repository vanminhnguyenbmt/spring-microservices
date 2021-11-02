package com.nguyenvm.orderservice.model.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyenvm.common.util.ContextUtil;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JsonSerializer<T> implements Serializer<T> {
    @Override
    public byte[] serialize(String topic, T data) {
        if (Objects.isNull(data)) return null;

        try {
            return ContextUtil.getBean(ObjectMapper.class).writeValueAsString(data).getBytes();
        } catch (JsonProcessingException e) {
            throw new SerializationException("Error serializing JSON message", e);
        }
    }
}
