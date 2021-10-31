package com.nguyenvm.galleryservice.model.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyenvm.common.util.ContextUtil;
import com.nguyenvm.galleryservice.model.dto.GalleryDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GalleryDTOSerializer implements Serializer<GalleryDTO> {
    @Override
    public byte[] serialize(String s, GalleryDTO galleryDTO) {
        try {
            return ContextUtil.getBean(ObjectMapper.class).writeValueAsString(galleryDTO).getBytes();
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return new byte[0];
    }
}
