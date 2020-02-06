package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.services.PlayerService;
import com.vizientinc.dungeonbase.requests.PlayerRequest;
import com.vizientinc.dungeonbase.responses.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("players")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(
        PlayerService playerService
    ) {
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    public PlayerResponse get(@PathVariable String id) throws ResourceNotFound {
        return new PlayerResponse(
            playerService.findById(id)
        );
    }

    @PostMapping
    public PlayerResponse post(@RequestBody PlayerRequest playerRequest) throws ResourceNotFound {
        return new PlayerResponse(
            playerService.save(playerRequest)
        );
    }

    @PutMapping
    public PlayerResponse put(@RequestBody PlayerRequest playerRequest) throws ResourceNotFound {
        return new PlayerResponse(
            playerService.updatePlayer(playerRequest)
        );
    }
}
