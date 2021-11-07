package com.nguyenvm.orderservice.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nguyenvm.orderservice.model.dto.OrderDTO;
import com.nguyenvm.orderservice.model.dto.mapper.OrderEntityToDTOMapper;
import com.nguyenvm.orderservice.model.entity.OrderEntity;
import com.nguyenvm.orderservice.service.OrderService;
import com.nguyenvm.orderservice.service.kafka.OrderProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderProducer orderProducer;

    @HystrixCommand(fallbackMethod = "fallBack")
    public OrderDTO getOrder(Integer id, Boolean isFallBack) {
        log.info("Creating order object ... ");

        // create order object
        OrderEntity order = new OrderEntity();
        order.setId(id);

        // get list of available products
        // isFallBack = true will make a fallback call
        List<Object> products = restTemplate.getForObject("http://stock-service/product/getAll?isFallBack=" + isFallBack, List.class);
        OrderDTO orderDTO = OrderEntityToDTOMapper.base(order, CollectionUtils.isEmpty(products) ? Collections.EMPTY_LIST : products);

        // send message to order-topic
//        orderProducer.produceOrderTopic(orderDTO);
        orderProducer.produceOrderTopicUsingKafkaTemplate(orderDTO);
        return orderDTO;
    }

    // a fallback method to be called if failure happened
    public OrderDTO fallBack(Integer id, Boolean isFallBack, Throwable hystrixCommand) {
        log.error("Fallback getOrder ... ", hystrixCommand);

        OrderDTO orderDTO = new OrderDTO(id, null, 0);

        // send message to order-topic
        orderProducer.produceOrderTopic(orderDTO);
        return orderDTO;
    }
}
