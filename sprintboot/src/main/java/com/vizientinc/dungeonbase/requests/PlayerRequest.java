package com.vizientinc.dungeonbase.requests;

import com.vizientinc.dungeonbase.models.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequest {
    String id;
    String name;
    List<Item> inventory;
    String location;

}
