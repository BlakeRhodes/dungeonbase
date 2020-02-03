package com.vizientinc.dungeonbase.repositories;

import com.vizientinc.dungeonbase.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventRepository extends MongoRepository<Event, String> {
    public Event findByType(String type);

    public List<Event> findByParent(String parentId);
}
