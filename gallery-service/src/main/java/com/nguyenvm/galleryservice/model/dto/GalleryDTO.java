package com.nguyenvm.galleryservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GalleryDTO {
    private Integer id;
    private List<Object> images;
}
