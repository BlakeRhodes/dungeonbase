package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.responses.LocationResponse;
import com.vizientinc.dungeonbase.services.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(
        LocationService locationService
    ) {
        this.locationService = locationService;
    }

    @GetMapping("/{id}")
    public LocationResponse get(@PathVariable String id) {
        return locationService.findById(id);
    }
}
