package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.event.OrderEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public OrderController(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public void createOrder(@RequestBody Order order) {
        try {
            OrderEvent event = new OrderEvent(order.getProduct(), order.getQuantity());
            String eventJson = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("order-topic", eventJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}