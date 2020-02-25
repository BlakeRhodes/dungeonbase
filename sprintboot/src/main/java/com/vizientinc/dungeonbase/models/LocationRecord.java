package com.vizientinc.dungeonbase.models;

import com.vizientinc.dungeonbase.controllers.LocationController;
import com.vizientinc.dungeonbase.interfaces.ItemLocation;
import com.vizientinc.dungeonbase.requests.LocactionRequest;
import lombok.Data;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
public class LocationRecord implements ItemLocation {
    String id;
    String name;
    String description;
    List<String> related;

    public LocationRecord(String name, String description, List<String> related){
        this.setName(name);
        this.setDescription(description);
        this.setRelated(related);
    }

    public LocationRecord(LocactionRequest locactionRequest){
        copyFromRecord(locactionRequest);
    }

    public void update(LocactionRequest locactionRequest){
        copyFromRecord(locactionRequest);
    }

    private void copyFromRecord(LocactionRequest locactionRequest) {
        this.setName(locactionRequest.getName());
        this.setDescription(locactionRequest.getDescription());
        this.setRelated(locactionRequest.getRelated());
    }

    @Override
    public WebMvcLinkBuilder getLink() throws Exception {
        return linkTo(methodOn(LocationController.class).get(id));
    }
}
