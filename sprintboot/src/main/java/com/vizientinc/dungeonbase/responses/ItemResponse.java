package com.vizientinc.dungeonbase.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.vizientinc.dungeonbase.controllers.ItemController;
import com.vizientinc.dungeonbase.domains.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@EqualsAndHashCode(callSuper = true)
@Data
public class ItemResponse extends RepresentationModel<LocationResponse> {
    String id;
    String name;
    String description;
    String location;

    @JsonCreator
    public ItemResponse(Item item) throws Exception {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.location = item.getLocationId();

        this.add(
            linkTo(methodOn(ItemController.class).get(item.getId()))
                .withSelfRel()
        );

        this.add(
            item.getLocationLink()
                .withRel("location")
        );
    }
}
