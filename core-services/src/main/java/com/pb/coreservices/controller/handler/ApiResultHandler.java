package com.pb.coreservices.controller.handler;

import org.springframework.http.ResponseEntity;

public interface ApiResultHandler<T> {

    ResponseEntity<T> handle(T t);

    ResponseEntity<T> handle(Exception exception);

    ResponseEntity<T> handle(T t, Exception exception);

}
