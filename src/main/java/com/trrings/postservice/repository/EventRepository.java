package com.trrings.postservice.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.trrings.postservice.entity.Event;

public interface EventRepository extends MongoRepository<Event,Long> {
}
