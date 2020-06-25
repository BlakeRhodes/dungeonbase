package com.vizientinc.dungeonbase.services;

import com.vizientinc.dungeonbase.domains.Item;
import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.models.ItemRecord;
import com.vizientinc.dungeonbase.repositories.ItemRepository;
import com.vizientinc.dungeonbase.requests.ItemRequest;
import com.vizientinc.dungeonbase.responses.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemLocationService itemLocationService;

    @Autowired
    public ItemService(
        ItemRepository itemRepository,
        ItemLocationService itemLocationService
    ) {
        this.itemRepository = itemRepository;
        this.itemLocationService = itemLocationService;
    }

    public List<ItemResponse> findAll() {
        return
            itemRepository.findAll()
                .stream()
                .map(record -> {
                    try {
                        return new ItemResponse(getItemById(record.getId()));
                    } catch(Exception resourceNotFound) {
                        resourceNotFound.printStackTrace();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(toList());
    }

    public Item getItemById(String id) throws ResourceNotFound {
        ItemRecord itemRecord = itemRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound(
                "item",
                id
            ));
        return new Item(
            itemRecord,
            itemLocationService.findLocation(itemRecord.getLocation())
        );
    }

    public Item update(ItemRequest itemRequest) throws ResourceNotFound {
        ItemRecord itemRecord = getItemRecordById(itemRequest.getId());
        itemRecord.update(itemRequest);
        return new Item(
            itemRepository.save(itemRecord),
            itemLocationService.findLocation(itemRequest.getLocation())
        );
    }

    public Item save(ItemRequest itemRequest) throws ResourceNotFound {
        return new Item(
            itemRepository.save(new ItemRecord(itemRequest)),
            itemLocationService.findLocation(itemRequest.getLocation())
        );
    }

    public List<String> findItemsAtLocation(String location) {
        return itemRepository.findAllByLocation(location)
            .stream()
            .map(ItemRecord::getId)
            .collect(toList());
    }


    public void delete(String id) throws ResourceNotFound {
        itemRepository.delete(getItemRecordById(id));
    }

    private ItemRecord getItemRecordById(String id) throws ResourceNotFound {
        return itemRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound(
                "item",
                id
            ));
    }
}
