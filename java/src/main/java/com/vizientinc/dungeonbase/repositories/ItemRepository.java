package com.vizientinc.dungeonbase.repositories;

import com.vizientinc.dungeonbase.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    List<Item> findAllByLocation(String location);
}
