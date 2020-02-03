package com.vizientinc.dungeonbase.handlers.exceptions;

public class ResourceNotFound  extends RuntimeException {

    public ResourceNotFound(String type, String id) {
        super(type + " with id not found : " + id);
    }
}
