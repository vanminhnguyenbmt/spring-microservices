package com.example.galleryservice.controller;

import com.example.galleryservice.entity.GalleryEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @HystrixCommand(fallbackMethod = "fallBack")
    @RequestMapping("/{id}")
    public GalleryEntity getGallery(@PathVariable final int id) {
        LOGGER.info("Creating gallery object ... ");

        // create gallery object
        GalleryEntity gallery = new GalleryEntity();
        gallery.setId(id);

        // get list of available images
        List<Object> images = restTemplate.getForObject("http://image-service/images/true", List.class);
        gallery.setImages(images);

        return gallery;
    }

    // a fallback method to be called if failure happened
    public GalleryEntity fallBack(int galleryId, Throwable hystrixCommand) {
        LOGGER.error("Fallback getGallery ... ", hystrixCommand);
        return new GalleryEntity(galleryId);
    }
}
