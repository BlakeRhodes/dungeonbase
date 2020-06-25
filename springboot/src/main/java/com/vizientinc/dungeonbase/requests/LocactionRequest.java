package com.vizientinc.dungeonbase.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocactionRequest {
    String id;
    String name;
    String description;
    List<String> related;
}
