package com.trrings.postservice.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.triings.trringscommon.vo.AuthRequest;
import com.trrings.postservice.entity.Event;
import com.trrings.postservice.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaJSONConsumer {
    private final EventRepository eventRepository;

    @KafkaListener(topics = "my-topic", groupId = "post-service-group")
    public void consumeJSONMsg(List<?> rawMessages) {
        log.info("Consuming the message from my-topic : : {}", rawMessages);
        List<Event> collect = rawMessages.stream()
                .map(object -> {
                    AuthRequest authRequest = new ObjectMapper().convertValue(object, AuthRequest.class);
                    return Event.builder()
                            .id(UUID.randomUUID().toString())
                            .payload(Map.of("user", "user"))
                            .eventType("EMAIL")
                            .entityId(authRequest.getEmail())
                            .timestamp(Instant.now())
                            .build();
                }).toList();
        eventRepository.saveAll(collect);
    }
}
