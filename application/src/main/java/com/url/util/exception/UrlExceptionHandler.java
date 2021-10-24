package com.url.util.exception;

import com.url.util.model.UriError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UrlExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(HashException.class)
    protected ResponseEntity<Object> handleHashException(HashException hashException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UriError(hashException.getLocalizedMessage()));
    }

    @ExceptionHandler(KeyNotFoundException.class)
    protected ResponseEntity<Object> handleUriException(KeyNotFoundException keyNotFoundException) {
        return ResponseEntity.status(org.springframework.http.HttpStatus.BAD_GATEWAY).body(new UriError(keyNotFoundException.getLocalizedMessage()));
    }

}
