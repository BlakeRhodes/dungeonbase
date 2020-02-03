package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.models.Player;
import com.vizientinc.dungeonbase.repositories.EventRepository;
import com.vizientinc.dungeonbase.repositories.PlayerRepository;
import com.vizientinc.dungeonbase.requests.NewPlayerRequest;
import com.vizientinc.dungeonbase.responses.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("players")
public class PlayerController {
    private PlayerRepository playerRepository;
    private EventRepository eventRepository;

    @Autowired
    public PlayerController(
        PlayerRepository playerRepository,
        EventRepository eventRepository
    ) {
        this.playerRepository = playerRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/{id}")
    public PlayerResponse get(@PathVariable String id) {
        return new PlayerResponse(
            playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound(
                    "Player",
                    id
                ))
        );
    }

    @PostMapping
    public PlayerResponse post(@RequestBody NewPlayerRequest newPlayerRequest) {
        Player player = new Player(newPlayerRequest);

        player.setCurrent(
            eventRepository.findByType("Start")
                .getId()
        );

        return new PlayerResponse(
            playerRepository.save(player)
        );
    }
}
