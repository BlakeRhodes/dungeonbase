package com.vizientinc.dungeonbase.interfaces;

import com.vizientinc.dungeonbase.handlers.exceptions.ResourceNotFound;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public interface ItemLocation {
    WebMvcLinkBuilder getLink() throws ResourceNotFound;
}
