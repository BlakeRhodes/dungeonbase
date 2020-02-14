package com.vizientinc.dungeonbase.services;

import com.vizientinc.dungeonbase.domains.Item;
import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.models.ItemRecord;
import com.vizientinc.dungeonbase.repositories.ItemRepository;
import com.vizientinc.dungeonbase.requests.ItemRequest;
import com.vizientinc.dungeonbase.responses.ItemResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.stream.Collectors.toList;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemLocationService itemLocationService;

    public ItemService(
        ItemRepository itemRepository,
        ItemLocationService itemLocationService
    ) {
        this.itemRepository = itemRepository;
        this.itemLocationService = itemLocationService;
    }

    @Async
    public CompletableFuture<Item> getItemById(String id) throws ResourceNotFound {
        ItemRecord itemRecord =  itemRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound(
                "item",
                id
            ));
        return completedFuture(new Item(
            itemRecord,
            itemLocationService.findLocation(itemRecord.getLocation())
        ));
    }

    @Async
    public CompletableFuture<Item> update(ItemRequest itemRequest) throws ResourceNotFound {
        ItemRecord itemRecord = getItemRecordById(itemRequest.getId());
        itemRecord.update(itemRequest);
        return completedFuture(new Item(
            itemRepository.save(itemRecord),
            itemLocationService.findLocation(itemRequest.getLocation())
        ));
    }

    @Async
    public CompletableFuture<Item> save(ItemRequest itemRequest) throws ResourceNotFound {
        return completedFuture(new Item(
            itemRepository.save(new ItemRecord(itemRequest)),
            itemLocationService.findLocation(itemRequest.getLocation())
        ));
    }

    @Async
    public CompletableFuture<List<String>> findItemsAtLocation(String location) {
        return completedFuture(itemRepository.findAllByLocation(location).stream()
     .map(ItemRecord::getId)
     .collect(toList()));
    }

    @Async
    public void delete(String id) throws ResourceNotFound {
        itemRepository.delete(getItemRecordById(id));
    }

    @Async
    public CompletableFuture<List<ItemResponse>> findAll() {
        return completedFuture(
            itemRepository.findAll()
                 .stream()
                 .map(record -> {
                     try {
                         return new ItemResponse(getItemById(record.getId()).get());
                     } catch(Exception resourceNotFound) {
                         resourceNotFound.printStackTrace();
                     }
                     return null;
                 })
                 .filter(Objects::nonNull)
                 .collect(toList())
        );
    }

    private ItemRecord getItemRecordById(String id) throws ResourceNotFound {
        return itemRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound(
                "item",
                id
            ));
    }
}
