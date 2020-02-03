package com.vizientinc.dungeonbase.models;

import com.vizientinc.dungeonbase.requests.NewPlayerRequest;
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
    String current;

    public Player(NewPlayerRequest newPlayerRequest) {
        name = newPlayerRequest.name;
        inventory = new ArrayList<>();
    }
}
