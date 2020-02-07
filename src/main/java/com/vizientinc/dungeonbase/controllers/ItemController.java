package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
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
    public ItemResponse get(@PathVariable String id) throws ResourceNotFound {
        Item item = itemService.getItemById(id);
        return getItemResponse(item);
    }

    @PostMapping
    public ItemResponse post(@RequestBody ItemRequest itemRequest) throws ResourceNotFound {
        Item item = itemService.save(
            new Item(itemRequest)
        );
        return getItemResponse(item);
    }

    @PutMapping
    public ItemResponse put(@RequestBody ItemRequest itemRequest) throws ResourceNotFound {
        Item item = itemService.getItemById(itemRequest.getId());
        item.update(itemRequest);
        item = itemService.save(item);

        return getItemResponse(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws ResourceNotFound {
        itemService.delete(id);
    }

    private ItemResponse getItemResponse(Item item) throws ResourceNotFound {
        return new ItemResponse(
            item,
            itemService.findItemLocation(item.getLocation())
        );
    }
}
