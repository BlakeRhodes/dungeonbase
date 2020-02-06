package com.vizientinc.dungeonbase.services;

import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.models.Player;
import com.vizientinc.dungeonbase.repositories.LocationRepository;
import com.vizientinc.dungeonbase.repositories.PlayerRepository;
import com.vizientinc.dungeonbase.requests.PlayerRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final LocationRepository locationRepository;

    public PlayerService(
        PlayerRepository playerRepository,
        LocationRepository locationRepository
    ) {
        this.playerRepository = playerRepository;
        this.locationRepository = locationRepository;
    }

    public Player findById(String id){
            return playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Player", id));
    }

    public Player save(PlayerRequest playerRequest){
        Player player = new Player(playerRequest);

        player.setLocation(
            locationRepository.findByName("Start")
                .getId()
        );

            return playerRepository.save(player);
    }

    public Player updatePlayer(PlayerRequest playerRequest){
        Player player = findById(playerRequest.getId());
        player.update(playerRequest);
        return playerRepository.save(player);
    }

    public List<Player> findByLocation(String locationId) {
        return playerRepository.findAllByLocation(locationId);
    }
}
