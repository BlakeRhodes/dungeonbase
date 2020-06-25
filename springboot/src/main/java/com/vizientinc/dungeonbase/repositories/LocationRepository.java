package com.vizientinc.dungeonbase.repositories;

import com.vizientinc.dungeonbase.models.LocationRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends MongoRepository<LocationRecord, String> {
    LocationRecord findByName(String name);
}
