package com.nguyenvm.stockservice.config;

import com.nguyenvm.common.config.properties.KafkaProperties;
import com.nguyenvm.common.model.kafka.JsonDeserializer;
import com.nguyenvm.stockservice.model.dto.OrderDTO;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
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

    @Bean("consumerConfig")
    public Map<String, Object> consumerConfig() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.CLIENT_ID_CONFIG, kafkaProperties.getClientId());
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getGroupId());
        configs.put(ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, kafkaProperties.getAutoCreateTopic());
        configs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, OrderDTO.class);

        return configs;
    }

    @Bean
    public ConsumerFactory<String, OrderDTO> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderDTO> customKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
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
