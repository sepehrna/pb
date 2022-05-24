package com.pb.coreservices.domain.exception;

public class NotFoundException extends DomainException {

    public NotFoundException(String message) {
        super(message);
    }

    private static String getMessage(String entityName, String entityId) {
        return String.format("Entity %s with id %s has not found", entityName, entityId);
    }
}
