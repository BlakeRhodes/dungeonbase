package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.requests.LocactionRequest;
import com.vizientinc.dungeonbase.responses.LocationResponse;
import com.vizientinc.dungeonbase.services.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(
        LocationService locationService
    ) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<LocationResponse> get(){
        return null;
    }

    @GetMapping("/{id}")
    public LocationResponse get(@PathVariable String id) throws Exception {

        LocationResponse locationResponse = locationService.findById(id)
            .get();
        return locationResponse;
    }

    @PostMapping
    public LocationResponse post(@RequestBody LocactionRequest locactionRequest){
        return null;
    }

    @PutMapping
    public LocationResponse put(@RequestBody LocactionRequest locactionRequest){
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
    }
}
