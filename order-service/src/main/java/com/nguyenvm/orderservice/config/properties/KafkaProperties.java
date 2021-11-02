package com.nguyenvm.orderservice.config.properties;

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

    @Value("${kafka.buffer.memory:}")
    private String bufferMemory;

    @Value("${kafka.retries:}")
    private Integer retries;

    @Value("${kafka.transactional.id:}")
    private String transactionId;
}
