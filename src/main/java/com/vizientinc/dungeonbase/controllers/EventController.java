package com.vizientinc.dungeonbase.controllers;

import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import com.vizientinc.dungeonbase.models.Event;
import com.vizientinc.dungeonbase.repositories.EventRepository;
import com.vizientinc.dungeonbase.requests.NewEventRequest;
import com.vizientinc.dungeonbase.responses.EventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("events")
public class EventController {

    private EventRepository eventRepository;

    @Autowired
    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/{id}")
    public EventResponse get(@PathVariable String id) {
        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound(
                "Event",
                id
            ));

        return new EventResponse(
            event,
            getChildren(event)
        );
    }

    @PostMapping
    public EventResponse post(@RequestBody NewEventRequest newEventRequest) {
        Event event = eventRepository.save(new Event(newEventRequest));

        return new EventResponse(
            event,
            getChildren(event)
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        eventRepository.deleteById(id);
    }

    @PutMapping
    public EventResponse put(@RequestBody Event event){
      event = eventRepository.save(event);

      return new EventResponse(
          event,
          getChildren(event)
      );
    }

    private List<String> getChildren(Event event) {
        return eventRepository.findByParent(event.getId())
            .stream()
            .map(Event::getId)
            .collect(toList());
    }
}
