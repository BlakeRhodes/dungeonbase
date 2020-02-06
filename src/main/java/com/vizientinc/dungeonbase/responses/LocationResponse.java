package com.vizientinc.dungeonbase.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.vizientinc.dungeonbase.controllers.ItemController;
import com.vizientinc.dungeonbase.controllers.LocationController;
import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.models.Item;
import com.vizientinc.dungeonbase.models.Location;
import com.vizientinc.dungeonbase.models.Player;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@EqualsAndHashCode(callSuper = true)
@Data
public class LocationResponse extends RepresentationModel<LocationResponse> {
    private final String id;
    private final String name;
    private final String description;
    private final List<String> related;

    @JsonCreator
    public LocationResponse(
        Location location,
        List<Location> adjacentLocations,
        List<Player> players,
        List<Item> items
    ) throws ResourceNotFound {
        this.id = location.getId();
        this.name = location.getName();
        this.description = location.getDescription();
        this.related = location.getRelated();

        this.add(
            linkTo(methodOn(LocationController.class).get(location.getId()))
                .withSelfRel()
        );

        for(Location adjacentLocation : adjacentLocations) {
            this.add(
                adjacentLocation.getLink()
                    .withRel(adjacentLocation.getName())
            );
        }

        for(Player player : players) {
            this.add(
                player.getLink()
                    .withRel("players")
            );
        }

        for(Item item : items) {
            this.add(
                linkTo(methodOn(ItemController.class).get(item.getId()))
                    .withRel("items")
            );
        }

    }
}
