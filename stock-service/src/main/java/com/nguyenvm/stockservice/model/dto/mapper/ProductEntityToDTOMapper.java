package com.nguyenvm.stockservice.model.dto.mapper;

import com.nguyenvm.stockservice.model.dto.ProductDTO;
import com.nguyenvm.stockservice.model.entity.ProductEntity;

public class ProductEntityToDTOMapper {
    public static ProductDTO base(ProductEntity productEntity) {
        return ProductDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .quantity(productEntity.getQuantity())
                .price(productEntity.getPrice())
                .build();
    }
}
