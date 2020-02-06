package com.vizientinc.dungeonbase.models;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public interface ItemLocation {
    WebMvcLinkBuilder getLink();
}
