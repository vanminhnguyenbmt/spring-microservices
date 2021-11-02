package com.nguyenvm.stockservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    private Integer id;
    private String name;
    private Integer quantity;
    private Integer price;
}
