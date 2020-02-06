package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.responses.LocationResponse;
import com.vizientinc.dungeonbase.services.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerLocationController {
    private final LocationService locationService;

    public PlayerLocationController(
        LocationService locationService
    ) {
        this.locationService = locationService;
    }

    @GetMapping("players/{playerId}/locations")
    public LocationResponse get(@PathVariable String playerId) {
        return locationService.findByPlayerId(playerId);
    }
}
