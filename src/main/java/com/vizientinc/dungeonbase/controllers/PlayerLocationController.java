package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.repositories.LocationRepository;
import com.vizientinc.dungeonbase.repositories.PlayerRepository;
import com.vizientinc.dungeonbase.responses.LocationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerLocationController {
    private LocationController locationController;
    private LocationRepository locationRepository;
    private PlayerRepository playerRepository;

    public PlayerLocationController(
        LocationController locationController,
        LocationRepository locationRepository,
        PlayerRepository playerRepository
    ) {
        this.locationController = locationController;
        this.locationRepository = locationRepository;
        this.playerRepository = playerRepository;
    }

    @GetMapping("players/{playerId}/locations")
    public LocationResponse get(@PathVariable String playerId) {

        return locationController.get(
            locationRepository.findById(
                playerRepository.findById(playerId)
                    .orElseThrow(
                        () -> new ResourceNotFound(
                            "Player",
                            playerId
                        )
                    )
                    .getLocation()
            )
                .orElseThrow(
                    () -> new ResourceNotFound(
                        "Player",
                        playerId
                    )
                )
                .getId());
    }
}
