package com.nguyenvm.stockservice.service.impl;

import com.nguyenvm.stockservice.model.dto.ProductDTO;
import com.nguyenvm.stockservice.model.dto.mapper.ProductEntityToDTOMapper;
import com.nguyenvm.stockservice.model.entity.ProductEntity;
import com.nguyenvm.stockservice.service.StockService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    private List<ProductEntity> mockList() {
        return Arrays.asList(
                new ProductEntity(1, "Coffee 1", 3, 30000),
                new ProductEntity(2, "Coffee 2", 5, 50000),
                new ProductEntity(3, "Coffee 3", 7, 70000)
        );
    }

    public List<ProductDTO> getListProduct(Boolean isFallBack) throws Exception {
        // testing fallback for order-service
        if (isFallBack) {
            throw new Exception("Products can't be fetched");
        }

        return mockList()
                .stream()
                .map(ProductEntityToDTOMapper::base)
                .collect(Collectors.toList());
    }
}
