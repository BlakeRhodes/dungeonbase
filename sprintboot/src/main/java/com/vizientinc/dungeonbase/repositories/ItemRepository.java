package com.vizientinc.dungeonbase.repositories;

import com.vizientinc.dungeonbase.models.ItemRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<ItemRecord, String> {
    List<ItemRecord> findAllByLocation(String location);
}
