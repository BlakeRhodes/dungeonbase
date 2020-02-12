package com.vizientinc.dungeonbase.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {
    String id;
    String name;
    String description;
    String location;
}
