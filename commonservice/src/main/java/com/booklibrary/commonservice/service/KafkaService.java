package com.booklibrary.commonservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topicName, String msg) {
        kafkaTemplate.send(topicName, msg);
        log.info("Message send to topic: " + topicName);
    }
}
