package com.vizientinc.dungeonbase.interfaces;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public interface ItemLocation {
    WebMvcLinkBuilder getLink() throws Exception;
    String getId();
}
