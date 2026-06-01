package com.ecommerce.aiservice.kafka;

import com.ecommerce.aiservice.service.VectorStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductConsumer {

    private final VectorStoreService vectorStoreService;

    @KafkaListener(
            topics = "product-events",
            groupId = "ai-service-groups"
    )
    public void consume(ProductCreatedEventDTO eventDTO){
        vectorStoreService.store(eventDTO);
        log.info("product consumed in ai service with productid {}" , eventDTO.getId());
    }

}
