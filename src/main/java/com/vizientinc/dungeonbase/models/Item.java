package com.vizientinc.dungeonbase.models;

import com.vizientinc.dungeonbase.requests.ItemRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    String id;
    String name;
    String description;
    String location;

    public Item(ItemRequest itemRequest){
        this.name = itemRequest.getName();
        this.description = itemRequest.getDescription();
        this.location = itemRequest.getLocation();
    }
}
