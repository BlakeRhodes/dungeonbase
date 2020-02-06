package com.vizientinc.dungeonbase.models;

import lombok.Data;

import java.util.List;

@Data
public class Location {
    String id;
    String name;
    String description;
    List<String> related;

    public Location(String name, String description, List<String> related){
        this.name = name;
        this.description = description;
        this.related = related;
    }
}
