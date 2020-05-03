package com.korabelska.demo.exceptions;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String id) {
        super("Entity with the following id " + id + " was not found.");
    }
}
