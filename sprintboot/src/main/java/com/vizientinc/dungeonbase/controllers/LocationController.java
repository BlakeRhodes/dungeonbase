package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.requests.LocactionRequest;
import com.vizientinc.dungeonbase.responses.LocationResponse;
import com.vizientinc.dungeonbase.services.LocationResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/locations")
public class LocationController {
    private final LocationResponseService locationResponseService;

    @Autowired
    public LocationController(
        LocationResponseService locationResponseService
    ) {
        this.locationResponseService = locationResponseService;
    }

    @GetMapping
    public CollectionModel<LocationResponse> get(
        @RequestParam(required = false) Optional<Integer> page,
        @RequestParam(required = false) Optional<Integer> size
    ) throws Exception {

        return locationResponseService.findAll(PageRequest.of(
            page.orElse(0),
            size.orElse(5)
        ));
    }

    @GetMapping("/{id}")
    public LocationResponse get(@PathVariable String id) throws Exception {

        return locationResponseService.findById(id);
    }

    @PostMapping
    public LocationResponse post(@RequestBody LocactionRequest locactionRequest) {
        return null;
    }

    @PutMapping
    public LocationResponse put(@RequestBody LocactionRequest locactionRequest) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
    }
}
