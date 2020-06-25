package com.vizientinc.dungeonbase.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.vizientinc.dungeonbase.controllers.ItemController;
import com.vizientinc.dungeonbase.controllers.LocationController;
import com.vizientinc.dungeonbase.domains.Player;
import com.vizientinc.dungeonbase.models.PlayerRecord;
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
    private final List<String> items;

    @JsonCreator
    public PlayerResponse(
        Player player
    ) throws Exception {
        this.id = player.getId();
        this.name = player.getName();
        this.location = player.getLocationId();
        this.items = player.getInventory();

        this.add(
            player.getLink()
                .withSelfRel()
        );

        this.add(
            linkTo(methodOn(LocationController.class).get(player.getLocationId()))
            .withRel("location")
        );

        for(String item: items){
            this.add(
                linkTo(methodOn(ItemController.class).get(item))
                .withRel("items")
            );
        }
    }
}