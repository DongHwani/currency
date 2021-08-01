package com.example.wirebarley.currency.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExchangeApiResponse {

    private boolean success;
    private String source;
    private Map<String, BigDecimal> quotes;
    private CurrencyLayerError error;

    public BigDecimal findExchangeRate(String currencyKey) {
        if(!quotes.containsKey(currencyKey)) {
            throw new RuntimeException("존재하지 않는 환율정보입니다");
        }
        return quotes.get(currencyKey);
    }

    public boolean hasError(){
        return error != null;
    }
}
