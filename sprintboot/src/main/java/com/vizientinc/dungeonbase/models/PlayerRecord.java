package com.vizientinc.dungeonbase.models;

import com.vizientinc.dungeonbase.controllers.PlayerController;
import com.vizientinc.dungeonbase.interfaces.ItemLocation;
import com.vizientinc.dungeonbase.requests.PlayerRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRecord implements ItemLocation {
    @Id
    String id;
    String name;
    String location;

    public PlayerRecord(PlayerRequest playerRequest) {
        name = playerRequest.getName();
    }

    public void update(PlayerRequest playerRequest) {
        name = playerRequest.getName();
        location = playerRequest.getLocation();
    }

    @Override
    public WebMvcLinkBuilder getLink() throws Exception {
        return linkTo(methodOn(PlayerController.class).get(id));
    }
}
