package com.nguyenvm.stockservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nguyenvm.stockservice.model.dto.ProductDTO;
import com.nguyenvm.stockservice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class StockController {
    @Autowired
    private StockService stockService;

    @HystrixCommand
    @GetMapping("/getAll")
    public List<ProductDTO> getListProduct(@RequestParam(name = "isFallBack", defaultValue = "false") boolean isFallBack) throws Exception {
        return stockService.getListProduct(isFallBack);
    }
}
