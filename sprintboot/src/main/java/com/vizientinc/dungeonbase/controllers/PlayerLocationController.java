package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.responses.LocationResponse;
import com.vizientinc.dungeonbase.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerLocationController {
    private final LocationService locationService;

    @Autowired
    public PlayerLocationController(
        LocationService locationService
    ) {
        this.locationService = locationService;
    }

    @GetMapping("v1/players/{playerId}/locations")
    public LocationResponse get(@PathVariable String playerId) throws Exception {
        return locationService.findByPlayerId(playerId);
    }
}
