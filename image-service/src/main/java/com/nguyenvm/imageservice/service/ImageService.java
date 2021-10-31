package com.nguyenvm.imageservice.service;

import com.nguyenvm.imageservice.model.dto.ImageDTO;

import java.util.List;

public interface ImageService {
    List<ImageDTO> getListImage(Boolean isFallBack) throws Exception;
}
