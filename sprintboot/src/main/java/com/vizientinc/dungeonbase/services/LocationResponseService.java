package com.vizientinc.dungeonbase.services;

import com.vizientinc.dungeonbase.controllers.LocationController;
import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.models.LocationRecord;
import com.vizientinc.dungeonbase.repositories.ItemRepository;
import com.vizientinc.dungeonbase.repositories.LocationRepository;
import com.vizientinc.dungeonbase.responses.LocationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class LocationResponseService {
    private final LocationRepository locationRepository;
    private final PlayerService playerService;
    private final ItemRepository itemRepository;

    @Autowired
    public LocationResponseService(
        LocationRepository locationRepository,
        PlayerService playerService,
        ItemRepository itemRepository
    ) {
        this.locationRepository = locationRepository;
        this.playerService = playerService;
        this.itemRepository = itemRepository;
    }

    public LocationResponse findById(
        String id
    ) throws Exception {
        LocationRecord locationRecord = locationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound(
                "Location",
                id
            ));

        return getLocationResponse(locationRecord);
    }

    public LocationResponse findByPlayerId(String playerId) throws Exception {
        return findById(playerService.findById(playerId)
                            .getLocationId());
    }

    public CollectionModel<LocationResponse> findAll(Pageable page) throws Exception {
        List<LocationResponse> found = new ArrayList<>();
        for(LocationRecord record : locationRepository.findAll(page)) {
            found.add(getLocationResponse(record));
        }

        CollectionModel<LocationResponse> result = new CollectionModel<>(
            found,
            linkTo(
                methodOn(LocationController.class)
                    .get(
                        Optional.of(page.getPageNumber()),
                        Optional.of(page.getPageSize())
                    )
            ).withSelfRel()
        );

        if(found.size() == page.getPageSize()){
             result.add(linkTo(
                methodOn(LocationController.class)
                    .get(
                        Optional.of(page.getPageNumber() + 1),
                        Optional.of(page.getPageSize())
                    )
                  ).withRel("next")
            );
        }

        if(page.getPageNumber() > 0){

        result.add(linkTo(
                methodOn(LocationController.class)
                    .get(
                        Optional.of(page.getPageNumber()-1),
                        Optional.of(page.getPageSize())
                    )
            ).withRel("previous"));
        }
        return result;
    }

    private LocationResponse getLocationResponse(LocationRecord locationRecord) throws Exception {
        return new LocationResponse(
            locationRecord,
            locationRecord.getRelated()
                .stream()
                .map(locationRepository::findByName)
                .collect(toList()),
            playerService.findByLocation(locationRecord.getId()),
            itemRepository.findAllByLocation(locationRecord.getId())
        );
    }
}
