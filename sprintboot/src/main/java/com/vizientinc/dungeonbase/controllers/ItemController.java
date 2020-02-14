package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.requests.ItemRequest;
import com.vizientinc.dungeonbase.responses.ItemResponse;
import com.vizientinc.dungeonbase.services.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(
        ItemService itemService
    ) {
        this.itemService = itemService;
    }

    @GetMapping()
    public List<ItemResponse> get() throws Exception{
        return itemService.findAll().get();
    }

    @GetMapping("/{id}")
    public ItemResponse get(@PathVariable String id) throws
        Exception {
        return new ItemResponse(
            itemService.getItemById(id).get()
        );
    }

    @PostMapping
    public ItemResponse post(@RequestBody ItemRequest itemRequest) throws Exception {
        return new ItemResponse(itemService.save(itemRequest).get());
    }

    @PutMapping
    public ItemResponse put(@RequestBody ItemRequest itemRequest) throws Exception {

        return new ItemResponse(
            itemService.update(itemRequest).get()
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws ResourceNotFound {
        itemService.delete(id);
    }
}
