package com.example.imageservice.controller;

import com.example.imageservice.entity.ImageEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/images/{isSuccessful}")
    public List<ImageEntity> getImages(@PathVariable(name = "isSuccessful") boolean isSuccessful) throws Exception {
        List<ImageEntity> images = Arrays.asList(
                new ImageEntity(1, "Treehouse of Horror V", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3842005760"),
                new ImageEntity(2, "The Town", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3698134272"),
                new ImageEntity(3, "The Last Traction Hero", "https://www.imdb.com/title/tt0096697/mediaviewer/rm1445594112"));

        // testing fallback for GalleryService
        if (!isSuccessful) {
            throw new Exception("Images can't be fetched");
        }

        return images;
    }
}
