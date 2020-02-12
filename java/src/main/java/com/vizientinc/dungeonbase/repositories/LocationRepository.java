package com.vizientinc.dungeonbase.repositories;

import com.vizientinc.dungeonbase.models.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends MongoRepository<Location, String> {
    Location findByName(String name);
}
