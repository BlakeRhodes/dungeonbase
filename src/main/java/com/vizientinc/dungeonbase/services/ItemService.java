package com.vizientinc.dungeonbase.services;

import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.models.Item;
import com.vizientinc.dungeonbase.interfaces.ItemLocation;
import com.vizientinc.dungeonbase.repositories.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemLocationService itemLocationService;

    public ItemService(
        ItemRepository itemRepository,
        ItemLocationService itemLocationService
    ) {this.itemRepository = itemRepository;
        this.itemLocationService = itemLocationService;
    }

    public Item getItemById(String id) throws ResourceNotFound {
        return itemRepository.findById(id)
            .orElseThrow(
                () -> new ResourceNotFound(
                    "item",
                    id
                )
            );
    }

    public Item save(Item item){
        return itemRepository.save(item);
    }

    public ItemLocation findItemLocation(String location) throws ResourceNotFound {
        return itemLocationService.findLocation(location);
    }
}
