package com.vizientinc.dungeonbase.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vizientinc.dungeonbase.controllers.EventController;
import com.vizientinc.dungeonbase.models.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@EqualsAndHashCode(callSuper = true)
@Data
public class EventResponse extends RepresentationModel<EventResponse> {

    private final Event event;

    @JsonCreator
    public EventResponse(
        @JsonProperty("event") Event event,
        List<String> children
    ) {
        this.event = event;
        this.add(
            linkTo(methodOn(EventController.class).get(event.getId()))
                .withSelfRel()
        );

        for(String child: children){
            this.add(
                linkTo(methodOn(EventController.class).get(child))
                    .withRel("paths")
            );
        }

        if(!event.getType().equals("Start"))
        this.add(
            linkTo(methodOn(EventController.class).get(event.getParent()))
                .withRel("back")
        );
    }
}
