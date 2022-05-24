package com.pb.coreservices.domain.service;

import com.pb.coreservices.domain.exception.DomainException;

import java.util.Optional;

/**
 * This class is a result handler for every validator which designed with the combinator pattern
 */
public interface ValidatorResult {

    static ValidatorResult valid() {
        return ValidatorSupport.valid();
    }

    static ValidatorResult invalid(DomainException exception) {
        return new Invalid(exception);
    }

    boolean isValid();

    Optional<DomainException> getException();


    final class Invalid implements ValidatorResult {

        private final DomainException exception;

        public Invalid(DomainException exception) {
            this.exception = exception;
        }

        @Override
        public boolean isValid() {
            return false;
        }

        @Override
        public Optional<DomainException> getException() {
            return Optional.of(exception);
        }
    }

    final class ValidatorSupport {
        private static final ValidatorResult valid = new ValidatorResult() {

            @Override
            public boolean isValid() {
                return true;
            }

            @Override
            public Optional<DomainException> getException() {
                return Optional.empty();
            }
        };

        static ValidatorResult valid() {
            return valid;
        }
    }
}
