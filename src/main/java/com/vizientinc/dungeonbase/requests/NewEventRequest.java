package com.vizientinc.dungeonbase.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewEventRequest {
    String description;
    String parent;
    String type;
    String requires;
    String adds;
}
