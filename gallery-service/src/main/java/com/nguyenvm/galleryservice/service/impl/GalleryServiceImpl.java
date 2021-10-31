package com.nguyenvm.galleryservice.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nguyenvm.galleryservice.config.properties.KafkaProperties;
import com.nguyenvm.galleryservice.model.dto.GalleryDTO;
import com.nguyenvm.galleryservice.model.dto.mapper.GalleryEntityToDTOMapper;
import com.nguyenvm.galleryservice.model.entity.GalleryEntity;
import com.nguyenvm.galleryservice.model.kafka.GalleryDTOSerializer;
import com.nguyenvm.galleryservice.service.GalleryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GalleryServiceImpl implements GalleryService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaProperties kafkaProperties;

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
        final Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProperties.getKeySerializer());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GalleryDTOSerializer.class.getName());
        configs.put(ProducerConfig.BUFFER_MEMORY_CONFIG, kafkaProperties.getBufferMemory());
        configs.put(ProducerConfig.RETRIES_CONFIG, kafkaProperties.getRetries());

        try (KafkaProducer producer = new KafkaProducer<String, String>(configs)) {
            final ProducerRecord message = new ProducerRecord(
                    "nguyenvm-topic",
                    "nguyenvm-key",
                    galleryDTO
            );
            producer.send(message);
        }
    }
}
