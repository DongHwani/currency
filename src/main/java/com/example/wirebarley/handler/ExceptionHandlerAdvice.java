package com.example.wirebarley.handler;

import com.example.wirebarley.currency.domain.exception.InvalidNumberMoneyException;
import com.example.wirebarley.currency.interfaces.exception.ExchangeApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {


    @ExceptionHandler(ExchangeApiException.class)
    public ResponseEntity exchangeApiException(ExchangeApiException e) {
        ErrorResource errorResource = new ErrorResource(e.getCode(), e.getMessage());
        return ResponseEntity.badRequest().body(errorResource);
    }

    @ExceptionHandler(InvalidNumberMoneyException.class)
    public ResponseEntity invalidNumberMoneyException(InvalidNumberMoneyException e) {
        ErrorResource errorResource = new ErrorResource(e.getMessage());
        return ResponseEntity.badRequest().body(errorResource);
    }
}
