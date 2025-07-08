package com.sourav.kafka.springkafkaservice.process;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
//        log.info("Producing message {} to topic {}", message, topic);
        kafkaTemplate.send(topic, message);
    }

    public void sendMessageWithKey(String topic, String message, String key) {
//        log.info("Producing message {} to topic {} with key value {}", message, topic, key);
        kafkaTemplate.send(topic, key, message);
    }

}
