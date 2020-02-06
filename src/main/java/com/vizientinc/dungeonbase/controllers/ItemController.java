package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.models.Item;
import com.vizientinc.dungeonbase.requests.ItemRequest;
import com.vizientinc.dungeonbase.responses.ItemResponse;
import com.vizientinc.dungeonbase.services.ItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(
        ItemService itemService
    ) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public ItemResponse get(@PathVariable String id) {
        Item item = itemService.getItemById(id);
        return new ItemResponse(item, itemService.findItemLocation(item.getLocation()));
    }

    @PostMapping
    public ItemResponse post(@RequestBody ItemRequest itemRequest) {
        Item item = itemService.save(
            new Item(itemRequest)
        );
        return new ItemResponse(item, itemService.findItemLocation(item.getLocation()));
    }
}
