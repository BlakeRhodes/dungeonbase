package com.vizientinc.dungeonbase.models;

import com.vizientinc.dungeonbase.controllers.LocationController;
import com.vizientinc.dungeonbase.interfaces.ItemLocation;
import lombok.Data;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
public class Location implements ItemLocation {
    String id;
    String name;
    String description;
    List<String> related;

    public Location(String name, String description, List<String> related){
        this.setName(name);
        this.setDescription(description);
        this.setRelated(related);
    }

    @Override
    public WebMvcLinkBuilder getLink() {
        return linkTo(methodOn(LocationController.class).get(id));
    }
}
