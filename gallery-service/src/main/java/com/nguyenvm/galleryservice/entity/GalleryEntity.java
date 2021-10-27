package com.nguyenvm.galleryservice.entity;

import java.util.List;

public class GalleryEntity {
    private Integer id;
    private List<Object> images;

    public GalleryEntity(Integer id, List<Object> images) {
        this.id = id;
        this.images = images;
    }

    public GalleryEntity() {
    }

    public GalleryEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Object> getImages() {
        return images;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }
}
