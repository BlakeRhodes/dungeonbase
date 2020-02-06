package com.vizientinc.dungeonbase.models;

import com.vizientinc.dungeonbase.requests.PlayerRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    String id;
    String name;
    List<Item> inventory;
    String location;

    public Player(PlayerRequest playerRequest) {
        name = playerRequest.getName();
        inventory = new ArrayList<>();
    }

    public void update(PlayerRequest playerRequest) {
        name = playerRequest.getName();
        inventory = playerRequest.getInventory();
        location = playerRequest.getLocation();
    }
}
