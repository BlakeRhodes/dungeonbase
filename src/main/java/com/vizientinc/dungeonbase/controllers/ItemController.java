package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.models.Item;
import com.vizientinc.dungeonbase.repositories.ItemRepository;
import com.vizientinc.dungeonbase.requests.ItemRequest;
import com.vizientinc.dungeonbase.responses.ItemResponse;
import com.vizientinc.dungeonbase.services.ItemLocationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("items")
public class ItemController {
    private final ItemRepository itemRepository;
    private final ItemLocationService itemLocationService;

    public ItemController(
        ItemRepository itemRepository,
        ItemLocationService itemLocationService
    ) {this.itemRepository = itemRepository;
        this.itemLocationService = itemLocationService;
    }

    @GetMapping("/{id}")
    public ItemResponse get(@PathVariable String id) {
        Item item = itemRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound(
                "item",
                id
            ));
        return new ItemResponse(item, itemLocationService.findLocation(item.getLocation()));
    }

    @PostMapping
    public ItemResponse post(@RequestBody ItemRequest itemRequest) {
        Item item = itemRepository.save(
            new Item(itemRequest)
        );
        return new ItemResponse(item, itemLocationService.findLocation(item.getLocation()));
    }
}
