package com.nguyenvm.imageservice.service.impl;

import com.nguyenvm.imageservice.model.dto.ImageDTO;
import com.nguyenvm.imageservice.model.dto.mapper.ImageEntityToDTOMapper;
import com.nguyenvm.imageservice.model.entity.ImageEntity;
import com.nguyenvm.imageservice.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    private List<ImageEntity> mockList() {
        return Arrays.asList(
                new ImageEntity(1, "Treehouse of Horror V", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3842005760"),
                new ImageEntity(2, "The Town", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3698134272"),
                new ImageEntity(3, "The Last Traction Hero", "https://www.imdb.com/title/tt0096697/mediaviewer/rm1445594112"));
    }

    public List<ImageDTO> getListImage(Boolean isFallBack) throws Exception {
        // testing fallback for GalleryService
        if (isFallBack) {
            throw new Exception("Images can't be fetched");
        }

        return mockList()
                .stream()
                .map(ImageEntityToDTOMapper::base)
                .collect(Collectors.toList());
    }
}
