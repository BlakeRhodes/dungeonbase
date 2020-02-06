package com.vizientinc.dungeonbase.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.vizientinc.dungeonbase.controllers.LocationController;
import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.models.Player;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@EqualsAndHashCode(callSuper = true)
@Data
public class PlayerResponse extends RepresentationModel<PlayerResponse> {

    private final String id;
    private final String name;
    private final String location;

    @JsonCreator
    public PlayerResponse(Player player) throws ResourceNotFound {
        this.id = player.getId();
        this.name = player.getName();
        this.location = player.getLocation();

        this.add(
            player.getLink()
                .withSelfRel()
        );

        this.add(
            linkTo(methodOn(LocationController.class).get(player.getLocation()))
            .withRel("location")
        );
    }
}