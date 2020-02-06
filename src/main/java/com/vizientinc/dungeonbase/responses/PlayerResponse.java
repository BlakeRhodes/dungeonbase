package com.vizientinc.dungeonbase.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vizientinc.dungeonbase.controllers.LocationController;
import com.vizientinc.dungeonbase.controllers.PlayerController;
import com.vizientinc.dungeonbase.models.Player;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@EqualsAndHashCode(callSuper = true)
@Data
public class PlayerResponse extends RepresentationModel<PlayerResponse> {

    private final Player player;

    @JsonCreator
    public PlayerResponse(@JsonProperty("player") Player player) {
        this.player = player;

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