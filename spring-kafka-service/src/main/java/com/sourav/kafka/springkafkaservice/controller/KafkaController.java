package com.sourav.kafka.springkafkaservice.controller;

import com.sourav.kafka.springkafkaservice.process.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping(value = "/publish")
    public String sendMessageToKafkaTopic(@RequestParam("message") String message) {
        String topic = "my-second-topic";
//        log.debug("Received request to publish message: {} to topic: {}", message, topic);
        kafkaProducerService.sendMessage(topic, message);
        return "Message '" + message + "' sent to topic '" + topic + "' successfully!";
    }

    @PostMapping("/publishWithKey")
    public String sendMessageWithKeyToKafkaTopic(@RequestParam("key") String key, @RequestParam("message") String message) {
        String topic = "my-second-topic"; // Use the same topic
//        log.debug("Received request to publish message with key: {}, value: {} to topic: {}", key, message, topic);
        kafkaProducerService.sendMessageWithKey(topic, key, message);
        return "Message with key '" + key + "', value '" + message + "' sent to topic '" + topic + "' successfully!";
    }

}
