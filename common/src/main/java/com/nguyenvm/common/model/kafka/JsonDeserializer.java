package com.nguyenvm.common.model.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyenvm.common.util.ContextUtil;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;
import java.util.Objects;

public class JsonDeserializer<T> implements Deserializer<T> {
    public static final String VALUE_CLASS_NAME_CONFIG = "value.class.name";
    private Class<T> className;

    @Override
    public void configure(Map<String, ?> props, boolean isKey) {
        className = (Class<T>) props.get(VALUE_CLASS_NAME_CONFIG);
    }

    @Override
    public T deserialize(String topic, byte[] bytes) {
        if (Objects.isNull(bytes)) return null;

        try {
            return ContextUtil.getBean(ObjectMapper.class).readValue(bytes, className);
        } catch (Exception e) {
            throw new SerializationException("Error deserializing JSON message", e);
        }
    }
}
