package com.example.orderservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class OrderKafkaListener {
    private static final Logger log = LoggerFactory.getLogger(OrderKafkaListener.class);
    private final ObjectMapper objectMapper;

    public OrderKafkaListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "order-status-topic", groupId = "order_group")
    public void listenOrderStatus(ConsumerRecord<String, String> record) {
        try {
            log.info("Received message: {}", record.value());
            log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}",
                    record.key(), record.partition(), record.topic(), record.timestamp());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}