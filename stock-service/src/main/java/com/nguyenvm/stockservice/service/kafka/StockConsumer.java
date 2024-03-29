package com.nguyenvm.stockservice.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyenvm.common.util.CommonConstants;
import com.nguyenvm.stockservice.model.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = {CommonConstants.ORDER_TOPIC}, containerFactory = "customKafkaListenerContainerFactory")
    public void consumerOrder(OrderDTO orderDTO) {
        try {
            log.info("[CONSUMER] Consumer order-topic: {}", objectMapper.writeValueAsString(orderDTO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
