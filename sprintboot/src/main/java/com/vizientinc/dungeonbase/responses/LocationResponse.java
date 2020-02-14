package com.vizientinc.dungeonbase.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.vizientinc.dungeonbase.controllers.ItemController;
import com.vizientinc.dungeonbase.controllers.LocationController;
import com.vizientinc.dungeonbase.models.ItemRecord;
import com.vizientinc.dungeonbase.models.LocationRecord;
import com.vizientinc.dungeonbase.models.PlayerRecord;
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
        LocationRecord locationRecord,
        List<LocationRecord> adjacentLocationRecords,
        List<PlayerRecord> playerRecords,
        List<ItemRecord> itemRecords
    ) throws Exception {
        this.id = locationRecord.getId();
        this.name = locationRecord.getName();
        this.description = locationRecord.getDescription();
        this.related = locationRecord.getRelated();

        this.add(
            linkTo(methodOn(LocationController.class).get(locationRecord.getId()))
                .withSelfRel()
        );

        for(LocationRecord adjacentLocationRecord : adjacentLocationRecords) {
            this.add(
                adjacentLocationRecord.getLink()
                    .withRel(adjacentLocationRecord.getName())
            );
        }

        for(PlayerRecord playerRecord : playerRecords) {
            this.add(
                playerRecord.getLink()
                    .withRel("players")
            );
        }

        for(ItemRecord itemRecord : itemRecords) {
            this.add(
                linkTo(methodOn(ItemController.class).get(itemRecord.getId()))
                    .withRel("items")
            );
        }

    }
}
