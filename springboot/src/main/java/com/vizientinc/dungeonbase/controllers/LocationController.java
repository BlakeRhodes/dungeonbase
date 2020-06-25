package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.requests.LocactionRequest;
import com.vizientinc.dungeonbase.responses.LocationResponse;
import com.vizientinc.dungeonbase.services.LocaitonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/locations")
public class LocationController {
    private final LocaitonService locaitonService;

    @Autowired
    public LocationController(
        LocaitonService locaitonService
    ) {
        this.locaitonService = locaitonService;
    }

    @GetMapping
    public CollectionModel<LocationResponse> get(
        @RequestParam(required = false) Optional<Integer> page,
        @RequestParam(required = false) Optional<Integer> size
    ) throws Exception {

        return locaitonService.findAll(
            PageRequest.of(
                page.orElse(0),
                size.orElse(5)
            )
        );
    }

    @GetMapping("/{id}")
    public LocationResponse get(@PathVariable String id) throws Exception {

        return locaitonService.findById(id);
    }

    @PostMapping
    public LocationResponse post(@RequestBody LocactionRequest locationRequest) throws Exception {
        return locaitonService.save(locationRequest);
    }

    @PutMapping
    public LocationResponse put(@RequestBody LocactionRequest locationRequest) throws Exception {
        return locaitonService.update(locationRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        locaitonService.delete(id);
    }
}
