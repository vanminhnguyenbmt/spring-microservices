package com.nguyenvm.galleryservice.service;

import com.nguyenvm.galleryservice.model.dto.GalleryDTO;

public interface GalleryService {
    GalleryDTO getGallery(Integer id, Boolean isFallBack);
}
