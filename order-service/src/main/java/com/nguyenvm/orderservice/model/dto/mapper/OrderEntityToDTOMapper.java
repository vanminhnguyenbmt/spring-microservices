package com.nguyenvm.orderservice.model.dto.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyenvm.common.util.ContextUtil;
import com.nguyenvm.orderservice.model.dto.OrderDTO;
import com.nguyenvm.orderservice.model.dto.ProductDTO;
import com.nguyenvm.orderservice.model.entity.OrderEntity;

import java.util.List;

public class OrderEntityToDTOMapper {
    public static OrderDTO base(OrderEntity orderEntity, List<Object> products) {
        List<ProductDTO> productDTOS = ContextUtil.getBean(ObjectMapper.class).convertValue(products, new TypeReference<List<ProductDTO>>() {});
        return OrderDTO.builder()
                .id(orderEntity.getId())
                .products(productDTOS)
                .totalAmount(productDTOS.stream().mapToInt(product -> product.getPrice() * product.getQuantity()).sum())
                .build();
    }
}
