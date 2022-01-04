package com.dwibagus.auths.kafka.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerLog {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.topic1}")
    private String topic;

    public void produce(String message) {
        kafkaTemplate.send(topic, message);
    }
}