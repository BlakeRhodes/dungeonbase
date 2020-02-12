package com.vizientinc.dungeonbase.repositories;

import com.vizientinc.dungeonbase.models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    List<Player> findAllByLocation(String locationId);
}
