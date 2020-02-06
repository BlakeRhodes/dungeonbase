package com.vizientinc.dungeonbase.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vizientinc.dungeonbase.controllers.ItemController;
import com.vizientinc.dungeonbase.models.Item;
import com.vizientinc.dungeonbase.interfaces.ItemLocation;
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
    public ItemResponse(Item item, ItemLocation itemLocation){
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.location = item.getLocation();

        this.add(
            linkTo(methodOn(ItemController.class).get(item.getId()))
                .withSelfRel()
        );

        this.add(
            itemLocation.getLink().withRel("location")
        );
    }
}
