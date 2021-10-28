package com.nguyenvm.imageservice.controller;

import com.nguyenvm.imageservice.entity.ImageEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    @HystrixCommand
    @GetMapping("/getAll")
    public List<ImageEntity> getImages(@RequestParam(name = "isFallBack", defaultValue = "false") boolean isFallBack) throws Exception {
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
