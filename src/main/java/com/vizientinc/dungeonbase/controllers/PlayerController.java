package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.models.Player;
import com.vizientinc.dungeonbase.repositories.LocationRepository;
import com.vizientinc.dungeonbase.repositories.PlayerRepository;
import com.vizientinc.dungeonbase.requests.PlayerRequest;
import com.vizientinc.dungeonbase.responses.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("players")
public class PlayerController {
    private PlayerRepository playerRepository;
    private LocationRepository locationRepository;

    @Autowired
    public PlayerController(
        PlayerRepository playerRepository,
        LocationRepository locationRepository
    ) {
        this.playerRepository = playerRepository;
        this.locationRepository = locationRepository;
    }

    @GetMapping("/{id}")
    public PlayerResponse get(@PathVariable String id) {
        return new PlayerResponse(
            playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Player", id))
        );
    }

    @PostMapping
    public PlayerResponse post(@RequestBody PlayerRequest playerRequest) {
        Player player = new Player(playerRequest);

        player.setLocation(
            locationRepository.findByName("Start")
                .getId()
        );

        return new PlayerResponse(
            playerRepository.save(player)
        );
    }

    @PutMapping
    public PlayerResponse put(@RequestBody PlayerRequest playerRequest) {
        Player player = playerRepository.findById(
            playerRequest.getId())
                .orElseThrow(() -> new ResourceNotFound("Player", playerRequest.getId())
            );

        player.update(playerRequest);

        return new PlayerResponse(
            playerRepository.save(player)
        );
    }
}
