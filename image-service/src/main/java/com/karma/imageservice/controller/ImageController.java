package com.karma.imageservice.controller;

import com.karma.imageservice.entity.ImageEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class ImageController {

    @HystrixCommand
    @RequestMapping("/images/{isFallBack}")
    public List<ImageEntity> getImages(@PathVariable(name = "isFallBack") boolean isFallBack) throws Exception {
        List<ImageEntity> images = Arrays.asList(
                new ImageEntity(1, "Treehouse of Horror V", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3842005760"),
                new ImageEntity(2, "The Town", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3698134272"),
                new ImageEntity(3, "The Last Traction Hero", "https://www.imdb.com/title/tt0096697/mediaviewer/rm1445594112"));

        // testing fallback for GalleryService
        if (isFallBack) {
            throw new Exception("Images can't be fetched");
        }

        return images;
    }
}
