package com.nguyenvm.imageservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nguyenvm.imageservice.model.dto.ImageDTO;
import com.nguyenvm.imageservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @HystrixCommand
    @GetMapping("/getAll")
    public List<ImageDTO> getImages(@RequestParam(name = "isFallBack", defaultValue = "false") boolean isFallBack) throws Exception {
        return imageService.getListImage(isFallBack);
    }
}
