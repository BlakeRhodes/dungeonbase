package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.models.Location;
import com.vizientinc.dungeonbase.repositories.LocationRepository;
import com.vizientinc.dungeonbase.responses.LocationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("locations")
public class LocationController {
    private LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {this.locationRepository = locationRepository;}

    @GetMapping("/{id}")
    public LocationResponse get(@PathVariable String id) {
        Location location = locationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound(
                "Location",
                id
            ));

        List<Location> adjacentLocations = location.getRelated()
            .stream()
            .map(adjacentLocation -> locationRepository
                .findByName(adjacentLocation)
            )
            .collect(toList());

        return new LocationResponse(
            location,
            adjacentLocations
        );
    }
}
