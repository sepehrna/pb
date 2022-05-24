package com.pb.coreservices.controller.handler;

import com.pb.coreservices.controller.dto.DataTransferObject;
import com.pb.coreservices.domain.exception.MandatoryFieldEmptyException;
import com.pb.coreservices.domain.exception.NotFoundException;
import org.springframework.http.ResponseEntity;

public class ApiResultHandlerImpl<T extends DataTransferObject> implements ApiResultHandler<T> {


    @Override
    public ResponseEntity<T> handle(T dataTransferObject) {
        return ResponseEntity
                .ok(dataTransferObject);
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
    public ResponseEntity<T> handle(T dataTransferObject, Exception exception) {
        if (exception instanceof MandatoryFieldEmptyException)
            return ResponseEntity
                    .badRequest()
                    .body(dataTransferObject);
        if (exception instanceof NotFoundException)
            return ResponseEntity
                    .notFound()
                    .build();
        return ResponseEntity
                .internalServerError()
                .build();
    }
}
