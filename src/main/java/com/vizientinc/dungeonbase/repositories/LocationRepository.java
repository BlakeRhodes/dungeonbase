package com.vizientinc.dungeonbase.repositories;

import com.vizientinc.dungeonbase.models.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {
    Location findByName(String start);
}
