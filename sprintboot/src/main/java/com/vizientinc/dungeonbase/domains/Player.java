package com.vizientinc.dungeonbase.domains;

import com.vizientinc.dungeonbase.models.PlayerRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private PlayerRecord playerRecord;
    private List<String> inventory;

    public String getId(){
        return playerRecord.getId();
    }

    public String getName(){
        return playerRecord.getName();
    }
    public String getLocationId(){
        return playerRecord.getLocation();
    }

    public WebMvcLinkBuilder getLink() throws Exception {
        return playerRecord.getLink();
    }
}
