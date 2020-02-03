package com.vizientinc.dungeonbase.repositories;

import com.vizientinc.dungeonbase.models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    public Player findById();
}
