package com.vizientinc.dungeonbase.services;

import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.interfaces.ItemLocation;
import com.vizientinc.dungeonbase.repositories.LocationRepository;
import com.vizientinc.dungeonbase.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemLocationService {

    private final PlayerRepository playerRepository;
    private final LocationRepository locationRepository;

    public ItemLocationService(
        PlayerRepository playerRepository,
        LocationRepository locationRepository
    ) {
        this.playerRepository = playerRepository;
        this.locationRepository = locationRepository;
    }

    public ItemLocation findLocation(String id) throws ResourceNotFound {
        ItemLocation itemLocation = playerRepository.findById(id).orElse(null);
        if(itemLocation != null){
            return itemLocation;
        }
        return locationRepository.findById(id).orElseThrow(() -> new ResourceNotFound("ItemLocation", id));
    }
}
