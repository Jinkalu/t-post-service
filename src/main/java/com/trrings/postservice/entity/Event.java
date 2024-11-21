package com.trrings.postservice.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "events")
public class Event {
    @Id
    private String id;

    private String entityId;

    private String eventType;

    private Instant timestamp;

    private Map<String, Object> payload;
}
