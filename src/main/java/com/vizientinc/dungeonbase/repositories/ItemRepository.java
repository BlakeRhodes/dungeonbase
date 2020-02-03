package com.vizientinc.dungeonbase.repositories;

import com.vizientinc.dungeonbase.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
}
