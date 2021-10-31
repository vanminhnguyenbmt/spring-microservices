package com.nguyenvm.imageservice.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ImageDTO {
    private Integer id;
    private String title;
    private String url;
}
