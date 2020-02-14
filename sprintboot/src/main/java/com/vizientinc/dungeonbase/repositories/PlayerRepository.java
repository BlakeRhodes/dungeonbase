package com.vizientinc.dungeonbase.repositories;

import com.vizientinc.dungeonbase.models.PlayerRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends MongoRepository<PlayerRecord, String> {
    List<PlayerRecord> findAllByLocation(String locationId);
}
