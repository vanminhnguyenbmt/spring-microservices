package com.nguyenvm.orderservice.service.kafka;

import com.nguyenvm.orderservice.model.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Slf4j
public class OrderProducer {
    private final static String topicTest1 = "nguyenvm-topic-1";
    private final static String topicTest2 = "nguyenvm-topic-2";
    private final static String orderTopic = "order-topic";

    @Resource
    @Qualifier("producerConfig")
    private Map<String, Object> producerConfig;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    @Qualifier("producerWithTransactionConfig")
    private Map<String, Object> producerWithTransactionConfig;

    public void produceOrderTopic(OrderDTO orderDTO) {
        try (KafkaProducer producer = new KafkaProducer<String, String>(producerConfig)) {
            producer.send(new ProducerRecord(orderTopic, orderDTO));
        }
    }

    // transaction kafka with producer message
    public void produceMessageWithTransaction(OrderDTO orderDTO) {
        KafkaProducer producer = new KafkaProducer<String, String>(producerWithTransactionConfig);
        producer.initTransactions();

        try {
            producer.beginTransaction();

            producer.send(new ProducerRecord(topicTest1, orderDTO));
            producer.send(new ProducerRecord(topicTest2, orderDTO));

            producer.commitTransaction();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            producer.abortTransaction();
            producer.close();
            throw new RuntimeException(e);
        }

        producer.close();
    }

    public void produceOrderTopicUsingKafkaTemplate(OrderDTO orderDTO) {
        kafkaTemplate.send(new ProducerRecord(orderTopic, orderDTO));
    }
}
