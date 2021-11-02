package com.nguyenvm.orderservice.model.kafka;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyenvm.common.util.ContextUtil;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JsonDeserializer<T> implements Deserializer<T> {
    @Override
    public T deserialize(String topic, byte[] bytes) {
        if (Objects.isNull(bytes)) return null;

        try {
            return ContextUtil.getBean(ObjectMapper.class).readValue(bytes, new TypeReference<T>() {});
        } catch (Exception e) {
            throw new SerializationException("Error deserializing JSON message", e);
        }
    }
}
