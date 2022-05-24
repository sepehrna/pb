package com.pb.coreservices.controller.handler;

import com.pb.coreservices.controller.dto.DataTransferObject;
import org.springframework.http.ResponseEntity;

public interface ApiResultHandler<T extends DataTransferObject> {

    ResponseEntity<T> handle(T dataTransferObject);

    ResponseEntity<T> handle(Exception exception);

    ResponseEntity<T> handle(T dataTransferObject, Exception exception);

}
