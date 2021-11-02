package com.nguyenvm.orderservice.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private Integer id;
    private List<ProductDTO> products;
    private Integer totalAmount;
}
