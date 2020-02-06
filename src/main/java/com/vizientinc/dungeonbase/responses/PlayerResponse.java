package com.vizientinc.dungeonbase.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vizientinc.dungeonbase.controllers.LocationController;
import com.vizientinc.dungeonbase.controllers.PlayerController;
import com.vizientinc.dungeonbase.models.Item;
import com.vizientinc.dungeonbase.models.Player;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@EqualsAndHashCode(callSuper = true)
@Data
public class PlayerResponse extends RepresentationModel<PlayerResponse> {

    private final String id;
    private final String name;
    private final String location;

    @JsonCreator
    public PlayerResponse(@JsonProperty("player") Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.location = player.getLocation();

        this.add(
            linkTo(methodOn(PlayerController.class).get(player.getId()))
                .withSelfRel()
        );

        this.add(
            linkTo(methodOn(LocationController.class).get(player.getLocation()))
            .withRel("location")
        );
    }
}