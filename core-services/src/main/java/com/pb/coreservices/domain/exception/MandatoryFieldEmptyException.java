package com.pb.coreservices.domain.exception;


public class MandatoryFieldEmptyException extends DomainException {


    public MandatoryFieldEmptyException(String entityName, String nullOrEmptyField) {
        super(getMessage(entityName, nullOrEmptyField));
    }

    private static String getMessage(String entityName, String nullOrEmptyField) {
        return String.format("The field %s in entity %s is null or empty but it must be not null", nullOrEmptyField, entityName);
    }

}
