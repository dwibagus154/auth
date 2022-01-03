package com.dwibagus.auths.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumer {
    private final static String topic = "bni46Topic";
    private final static String groupId = "bni46Group";
    public static List<String> messages = new ArrayList<>();

    @KafkaListener(topics = topic, groupId = groupId)
    public void listen(String message) {
        messages.add(message);
    }
}