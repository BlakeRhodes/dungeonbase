package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.requests.PlayerRequest;
import com.vizientinc.dungeonbase.responses.PlayerResponse;
import com.vizientinc.dungeonbase.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/players")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerResponse> get(){
        return null;
    }

    @GetMapping("/{id}")
    public PlayerResponse get(@PathVariable String id) throws Exception {

        return new PlayerResponse(
            playerService.findById(id)
        );
    }

    @PostMapping
    public PlayerResponse post(@RequestBody PlayerRequest playerRequest) throws Exception {
        return new PlayerResponse(
            playerService.save(playerRequest)
        );
    }

    @PutMapping
    public PlayerResponse put(@RequestBody PlayerRequest playerRequest) throws Exception {
        return new PlayerResponse(
            playerService.updatePlayer(playerRequest)
        );
    }

    @DeleteMapping("{id}")
    public void delete(String id){
        playerService.delete(id);
    }

}
