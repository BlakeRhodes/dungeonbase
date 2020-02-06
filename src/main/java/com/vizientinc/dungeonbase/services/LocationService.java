package com.vizientinc.dungeonbase.services;

import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.models.Location;
import com.vizientinc.dungeonbase.repositories.LocationRepository;
import com.vizientinc.dungeonbase.responses.LocationResponse;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final PlayerService playerService;

    public LocationService(
        LocationRepository locationRepository,
        PlayerService playerService
    ) {this.locationRepository = locationRepository;
        this.playerService = playerService;
    }

    public LocationResponse findById(String id) {
        Location location = locationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound(
                "Location",
                id
            ));

        return new LocationResponse(
            location,
            location.getRelated()
                .stream()
                .map(locationRepository::findByName)
                .collect(toList())
        );
    }

    public LocationResponse findByPlayerId(String playerId){
        return findById(playerService.findById(playerId).getLocation());
    }
}
