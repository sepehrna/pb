package com.pb.coreservices.controller.handler;

import com.pb.coreservices.domain.exception.MandatoryFieldEmptyException;
import com.pb.coreservices.domain.exception.NotFoundException;
import org.springframework.http.ResponseEntity;

public class ApiResultHandlerImpl<T> implements ApiResultHandler<T> {


    @Override
    public ResponseEntity<T> handle(T t) {
        return ResponseEntity
                .ok(t);
    }

    @Override
    public ResponseEntity<T> handle(Exception exception) {
        if (exception instanceof MandatoryFieldEmptyException)
            return ResponseEntity
                    .badRequest()
                    .build();
        if (exception instanceof NotFoundException)
            return ResponseEntity
                    .notFound()
                    .build();
        return ResponseEntity
                .internalServerError()
                .build();
    }

    @Override
    public ResponseEntity<T> handle(T t, Exception exception) {
        if (exception instanceof MandatoryFieldEmptyException)
            return ResponseEntity
                    .badRequest()
                    .body(t);
        if (exception instanceof NotFoundException)
            return ResponseEntity
                    .notFound()
                    .build();
        return ResponseEntity
                .internalServerError()
                .build();
    }
}
