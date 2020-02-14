package com.vizientinc.dungeonbase.services;

import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.models.LocationRecord;
import com.vizientinc.dungeonbase.repositories.ItemRepository;
import com.vizientinc.dungeonbase.repositories.LocationRepository;
import com.vizientinc.dungeonbase.responses.LocationResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.stream.Collectors.toList;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final PlayerService playerService;
    private final ItemRepository itemRepository;

    public LocationService(
        LocationRepository locationRepository,
        PlayerService playerService,
        ItemRepository itemRepository
    ) {
        this.locationRepository = locationRepository;
        this.playerService = playerService;
        this.itemRepository = itemRepository;
    }

    @Async
    public CompletableFuture<LocationResponse> findById(
        String id
    ) throws Exception {
        LocationRecord locationRecord = locationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound(
                "Location",
                id
            ));


        return completedFuture(new LocationResponse(
            locationRecord,
            locationRecord.getRelated()
                .stream()
                .map(locationRepository::findByName)
                .collect(toList()),
            playerService.findByLocation(locationRecord.getId()),
            itemRepository.findAllByLocation(locationRecord.getId())
        ));
    }

    public LocationResponse findByPlayerId(String playerId) throws Exception {
        return findById(playerService.findById(playerId).get().getLocationId()).get();
    }
}
