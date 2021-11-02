package com.nguyenvm.orderservice.controller;

import com.nguyenvm.orderservice.model.dto.OrderDTO;
import com.nguyenvm.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public OrderDTO getOrder(@RequestParam("id") Integer id,
                               @RequestParam(value = "isFallBack", defaultValue = "false") Boolean isFallBack) {
        return orderService.getOrder(id, isFallBack);
    }
}
