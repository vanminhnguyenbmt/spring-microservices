package com.nguyenvm.galleryservice.controller;

import com.nguyenvm.galleryservice.entity.GalleryEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/gallery")
@Log4j2
public class GalleryController {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallBack")
    @RequestMapping("/")
    public GalleryEntity getGallery(@RequestParam("id") Integer id, @RequestParam(value = "isFallBack", defaultValue = "false") Boolean isFallBack) {
        log.info("Creating gallery object ... ");

        // create gallery object
        GalleryEntity gallery = new GalleryEntity();
        gallery.setId(id);

        // get list of available images
        // isFallBack = true will make a fallback call
        List<Object> images = restTemplate.getForObject("http://image-service/images?isFallBack=" + isFallBack, List.class);
        gallery.setImages(images);

        return gallery;
    }

    // a fallback method to be called if failure happened
    public GalleryEntity fallBack(Integer galleryId, Boolean isFallBack, Throwable hystrixCommand) {
        log.error("Fallback getGallery ... ", hystrixCommand);
        return new GalleryEntity(galleryId);
    }
}
