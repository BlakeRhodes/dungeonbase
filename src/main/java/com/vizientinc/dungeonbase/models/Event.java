package com.vizientinc.dungeonbase.models;

import com.vizientinc.dungeonbase.requests.NewEventRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    String id;
    String description;
    String parent;
    String type;
    String requires;
    String adds;

    public Event(String description, String parent, String type){
        this.description = description;
        this.parent = parent;
        this.type = type;
    }

    public Event(NewEventRequest newEventRequest) {
        this.description = newEventRequest.getDescription();
        this.parent = newEventRequest.getParent();
        this.type = newEventRequest.getType();
        this.requires = newEventRequest.getRequires();
        this.adds = newEventRequest.getAdds();
    }
}

