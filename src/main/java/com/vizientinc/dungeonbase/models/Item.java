package com.vizientinc.dungeonbase.models;

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
}
