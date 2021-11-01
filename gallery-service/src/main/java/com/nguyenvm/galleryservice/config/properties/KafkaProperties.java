package com.nguyenvm.galleryservice.config.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KafkaProperties {
    @Value("${kafka.client.id:}")
    private String clientId;

    @Value("${spring.cloud.stream.kafka.binder.brokers:}")
    private String bootstrapServers;

    @Value("${kafka.key.serializer:}")
    private String keySerializer;

    @Value("${kafka.value.serializer:}")
    private String valueSerializer;

    @Value("${kafka.buffer.memory:}")
    private String bufferMemory;

    @Value("${kafka.retries:}")
    private Integer retries;

    @Value("${kafka.transactional.id:}")
    private String transactionId;
}
