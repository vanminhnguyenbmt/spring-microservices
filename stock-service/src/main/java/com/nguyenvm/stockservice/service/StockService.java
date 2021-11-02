package com.nguyenvm.stockservice.service;

import com.nguyenvm.stockservice.model.dto.ProductDTO;

import java.util.List;

public interface StockService {
    List<ProductDTO> getListProduct(Boolean isFallBack) throws Exception;
}
