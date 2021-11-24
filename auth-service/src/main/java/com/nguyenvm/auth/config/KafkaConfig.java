package com.nguyenvm.auth.config;

import com.nguyenvm.common.config.properties.KafkaProperties;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {
    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public Map<String, Object> adminConfig() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());

        return configs;
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(adminConfig());
    }

    @Bean
    public Admin admin() {
        Admin admin = Admin.create(adminConfig());
        return admin;
    }

    @Bean("zipkinProducerConfig")
    public Map<String, Object> zipkinProducerConfig() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProperties.getZipkinClientId());
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configs.put(ProducerConfig.ACKS_CONFIG, kafkaProperties.getZipkinAcks());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());

        return configs;
    }

    @Bean("zipkinProducerFactory")
    public ProducerFactory<String, String> zipkinProducerFactory() {
        return new DefaultKafkaProducerFactory<>(zipkinProducerConfig());
    }

    @Bean("zipkinKafkaTemplate")
    public KafkaTemplate<String, String> zipkinKafkaTemplate() {
        return new KafkaTemplate<>(zipkinProducerFactory());
    }
}
