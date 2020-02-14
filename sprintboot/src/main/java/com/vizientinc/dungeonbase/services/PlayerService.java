package com.vizientinc.dungeonbase.services;

import com.vizientinc.dungeonbase.domains.Player;
import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.models.PlayerRecord;
import com.vizientinc.dungeonbase.repositories.LocationRepository;
import com.vizientinc.dungeonbase.repositories.PlayerRepository;
import com.vizientinc.dungeonbase.requests.PlayerRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final LocationRepository locationRepository;
    private final ItemService itemService;

    public PlayerService(
        PlayerRepository playerRepository,
        LocationRepository locationRepository,
        ItemService itemService
    ) {
        this.playerRepository = playerRepository;
        this.locationRepository = locationRepository;
        this.itemService = itemService;
    }

    @Async
    public CompletableFuture<Player> findById(String id) throws Exception {
            return CompletableFuture.completedFuture(new Player(
                playerRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFound(
                        "Player",
                        id
                    )),
                itemService.findItemsAtLocation(id)
                    .get()
            ));
    }

    @Async
    public CompletableFuture<Player> save(PlayerRequest playerRequest) throws Exception {
        PlayerRecord playerRecord = new PlayerRecord(playerRequest);

        playerRecord.setLocation(
            locationRepository.findByName("Start")
                .getId()
        );

            return CompletableFuture.completedFuture(new Player(
                playerRepository.save(playerRecord),
                itemService.findItemsAtLocation(playerRecord.getId())
                    .get()
            ));
    }

    @Async
    public CompletableFuture<Player> updatePlayer(PlayerRequest playerRequest) throws Exception {
        PlayerRecord playerRecord = findById(playerRequest.getId())
            .get()
            .getPlayerRecord();
        playerRecord.update(playerRequest);
        return CompletableFuture.completedFuture(new Player(
            playerRepository.save(playerRecord),
            itemService.findItemsAtLocation(playerRecord.getId())
                .get()
        ));
    }

    public List<PlayerRecord> findByLocation(String locationId) {
        return playerRepository.findAllByLocation(locationId);
    }
}
