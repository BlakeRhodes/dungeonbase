package com.vizientinc.dungeonbase.domains;

import com.vizientinc.dungeonbase.interfaces.ItemLocation;
import com.vizientinc.dungeonbase.models.ItemRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    String id;
    String name;
    String description;
    ItemLocation location;

    public Item(ItemRecord itemRecord, ItemLocation itemLocation){
        id = itemRecord.getId();
        name = itemRecord.getName();
        description = itemRecord.getDescription();
        location = itemLocation;
    }

    public String getLocationId(){
        return location.getId();
    }

    public WebMvcLinkBuilder getLocationLink() throws Exception {
        return location.getLink();
    }
}
