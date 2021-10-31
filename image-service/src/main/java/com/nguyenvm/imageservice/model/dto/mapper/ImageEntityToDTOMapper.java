package com.nguyenvm.imageservice.model.dto.mapper;

import com.nguyenvm.imageservice.model.dto.ImageDTO;
import com.nguyenvm.imageservice.model.entity.ImageEntity;

public class ImageEntityToDTOMapper {
    public static ImageDTO base(ImageEntity imageEntity) {
        return ImageDTO.builder()
                .id(imageEntity.getId())
                .title(imageEntity.getTitle())
                .url(imageEntity.getUrl())
                .build();
    }
}
