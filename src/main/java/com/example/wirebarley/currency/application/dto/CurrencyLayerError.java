package com.example.wirebarley.currency.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CurrencyLayerError {

    private int code;
    private String info;

}
