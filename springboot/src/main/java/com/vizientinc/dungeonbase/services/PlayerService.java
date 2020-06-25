package com.vizientinc.dungeonbase.services;

import com.vizientinc.dungeonbase.domains.Player;
import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.models.PlayerRecord;
import com.vizientinc.dungeonbase.repositories.LocationRepository;
import com.vizientinc.dungeonbase.repositories.PlayerRepository;
import com.vizientinc.dungeonbase.requests.PlayerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final LocationRepository locationRepository;
    private final ItemService itemService;

    @Autowired
    public PlayerService(
        PlayerRepository playerRepository,
        LocationRepository locationRepository,
        ItemService itemService
    ) {
        this.playerRepository = playerRepository;
        this.locationRepository = locationRepository;
        this.itemService = itemService;
    }

    public Player findById(String id) throws Exception {
            return new Player(
                playerRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound(
                        "Player",
                        id
                    )),
                itemService.findItemsAtLocation(id)
            );
    }

    public Player save(PlayerRequest playerRequest) throws Exception {
        PlayerRecord playerRecord = new PlayerRecord(playerRequest);

        playerRecord.setLocation(
            locationRepository.findByName("Start")
                .getId()
        );

            return new Player(
                playerRepository.save(playerRecord),
                itemService.findItemsAtLocation(playerRecord.getId())
            );
    }

    public Player updatePlayer(PlayerRequest playerRequest) throws Exception {
        PlayerRecord playerRecord = findById(playerRequest.getId())
            .getPlayerRecord();
        playerRecord.update(playerRequest);
        return new Player(
            playerRepository.save(playerRecord),
            itemService.findItemsAtLocation(playerRecord.getId())
        );
    }

    public List<PlayerRecord> findByLocation(String locationId) {
        return playerRepository.findAllByLocation(locationId);
    }

    public void delete(String id) {
        playerRepository.deleteById(id);
    }
}
