package com.coders.laundry.error.handler;

import com.coders.laundry.error.ErrorResponse;
import com.coders.laundry.error.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BaseExceptionHandler {

  @ExceptionHandler(BaseException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
    return ResponseEntity.status(BaseException.DEFAULT_STATUS).body(e.buildErrorResponse());
  }
}
