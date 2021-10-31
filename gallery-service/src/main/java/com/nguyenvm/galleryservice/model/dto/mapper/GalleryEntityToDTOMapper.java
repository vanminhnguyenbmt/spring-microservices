package com.nguyenvm.galleryservice.model.dto.mapper;

import com.nguyenvm.galleryservice.model.dto.GalleryDTO;
import com.nguyenvm.galleryservice.model.entity.GalleryEntity;

public class GalleryEntityToDTOMapper {
    public static GalleryDTO base(GalleryEntity galleryEntity) {
        return GalleryDTO.builder()
                .id(galleryEntity.getId())
                .images(galleryEntity.getImages())
                .build();
    }
}
