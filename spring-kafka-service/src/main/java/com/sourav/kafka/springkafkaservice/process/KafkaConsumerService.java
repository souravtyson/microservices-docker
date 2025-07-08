package com.sourav.kafka.springkafkaservice.process;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

@Service
@Slf4j
public class KafkaConsumerService {

    private static final String TOPIC = "my-second-topic";

    @KafkaListener(topics = TOPIC, groupId="${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        System.out.println("Received message: " + message + " with topic : "+ TOPIC);
    }

    @KafkaListener(topics = TOPIC, groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void listenWithKeyAndPartition(String message,
                                          @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                          @Header(KafkaHeaders.OFFSET) long offset,
                                          @Header(KafkaHeaders.RECEIVED_KEY) String key,
                                          @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                          @Header(KafkaHeaders.TIMESTAMP_TYPE) String timeStampType) {
        System.out.println("Received message: "+message + "with Offset : " +offset + " with key : "+key + " with topic : "+ topic);
    }
}
