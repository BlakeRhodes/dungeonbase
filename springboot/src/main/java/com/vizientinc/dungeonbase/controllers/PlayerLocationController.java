package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.responses.LocationResponse;
import com.vizientinc.dungeonbase.services.LocaitonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerLocationController {
    private final LocaitonService locaitonService;

    @Autowired
    public PlayerLocationController(
        LocaitonService locaitonService
    ) {
        this.locaitonService = locaitonService;
    }

    @GetMapping("v1/players/{playerId}/locations")
    public LocationResponse get(@PathVariable String playerId) throws Exception {
        return locaitonService.findByPlayerId(playerId);
    }
}
