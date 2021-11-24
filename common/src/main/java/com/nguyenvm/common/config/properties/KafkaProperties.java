package com.nguyenvm.common.config.properties;

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

    @Value("${kafka.max.request.size:}")
    private Integer maxRequestSize;

    @Value("${kafka.max.partition.fetch.bytes:}")
    private Integer maxPartitionFetchByte;

    @Value("${kafka.transactional.id:}")
    private String transactionId;

    @Value("${kafka.group.id:}")
    private String groupId;

    @Value("${kafka.auto.create.topics:}")
    private Boolean autoCreateTopic;

    @Value("zipkin-service")
    private String zipkinClientId;

    @Value("0")
    private String zipkinAcks;
}