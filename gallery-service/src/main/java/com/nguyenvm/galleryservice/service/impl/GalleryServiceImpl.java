package com.nguyenvm.galleryservice.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nguyenvm.galleryservice.model.dto.GalleryDTO;
import com.nguyenvm.galleryservice.model.dto.mapper.GalleryEntityToDTOMapper;
import com.nguyenvm.galleryservice.model.entity.GalleryEntity;
import com.nguyenvm.galleryservice.service.GalleryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GalleryServiceImpl implements GalleryService {
    @Autowired
    private RestTemplate restTemplate;

    @Resource
    @Qualifier("producerConfig")
    private Map<String, Object> producerConfig;

    @HystrixCommand(fallbackMethod = "fallBack")
    public GalleryDTO getGallery(Integer id, Boolean isFallBack) {
        log.info("Creating gallery object ... ");

        // create gallery object
        GalleryEntity gallery = new GalleryEntity();
        gallery.setId(id);

        // get list of available images
        // isFallBack = true will make a fallback call
        List<Object> images = restTemplate.getForObject("http://image-service/image/getAll?isFallBack=" + isFallBack, List.class);
        gallery.setImages(images);

        GalleryDTO galleryDTO = GalleryEntityToDTOMapper.base(gallery);

        produceMessage(galleryDTO);
        return galleryDTO;
    }

    // a fallback method to be called if failure happened
    public GalleryDTO fallBack(Integer galleryId, Boolean isFallBack, Throwable hystrixCommand) {
        log.error("Fallback getGallery ... ", hystrixCommand);

        GalleryDTO galleryDTO = new GalleryDTO(galleryId, null);

        produceMessage(galleryDTO);
        return galleryDTO;
    }

    // produce message to kafka
    private void produceMessage(GalleryDTO galleryDTO) {
        KafkaProducer producer = new KafkaProducer<String, String>(producerConfig);
        producer.initTransactions();

        try {
            producer.beginTransaction();
            ProducerRecord message1 = new ProducerRecord(
                    "nguyenvm-topic-1",
                    galleryDTO
            );
            ProducerRecord message2 = new ProducerRecord(
                    "nguyenvm-topic-2",
                    galleryDTO
            );

            producer.send(message1);
            producer.send(message2);
            producer.commitTransaction();
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            producer.abortTransaction();
            producer.close();
            throw new RuntimeException(e);
        }

        producer.close();
    }
}
