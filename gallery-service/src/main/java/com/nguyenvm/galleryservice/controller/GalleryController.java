package com.nguyenvm.galleryservice.controller;

import com.nguyenvm.galleryservice.model.dto.GalleryDTO;
import com.nguyenvm.galleryservice.service.GalleryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gallery")
@Slf4j
public class GalleryController {
    @Autowired
    private GalleryService galleryService;

    @GetMapping
    public GalleryDTO getGallery(@RequestParam("id") Integer id,
                                 @RequestParam(value = "isFallBack", defaultValue = "false") Boolean isFallBack) throws Exception {
        return galleryService.getGallery(id, isFallBack);
    }
}
