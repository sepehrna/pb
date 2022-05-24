package com.pb.coreservices.domain.exception;

/**
 * DomainException is a RuntimeException that can happen because of a business rule violation
 */
public abstract class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }

}
