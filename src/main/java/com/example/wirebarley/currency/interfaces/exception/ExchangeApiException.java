package com.example.wirebarley.currency.interfaces.exception;

import com.example.wirebarley.currency.application.dto.CurrencyLayerError;
import lombok.Getter;

@Getter
public class ExchangeApiException extends RuntimeException {

    private int code;

    public ExchangeApiException(CurrencyLayerError error) {
        super(error.getInfo());
        this.code = error.getCode();
    }
}
