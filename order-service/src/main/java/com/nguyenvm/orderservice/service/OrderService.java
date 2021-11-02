package com.nguyenvm.orderservice.service;

import com.nguyenvm.orderservice.model.dto.OrderDTO;

public interface OrderService {
    OrderDTO getOrder(Integer id, Boolean isFallBack);
}
