package com.example.wirebarley.currency.interfaces.dto;

import com.example.wirebarley.currency.application.dto.ExchangeApiResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExchangeRateResponse {

    private static final DecimalFormat DECIMAL_EXPRESSION = new DecimalFormat("#,##0.00");

    private String currencyRelation;
    private BigDecimal rate;
    private String rateExpression;

    private ExchangeRateResponse(ExchangeApiResponse response, String targetCountry) {
        BigDecimal exchangeRate = response.findExchangeRate(response.getSource() + targetCountry);
        this.rateExpression = DECIMAL_EXPRESSION.format(exchangeRate);
        this.rate = exchangeRate.setScale(2, BigDecimal.ROUND_FLOOR);
        this.currencyRelation = targetCountry + "/" + response.getSource();
    }

    public static ExchangeRateResponse of(ExchangeApiResponse response, String targetCountry) {
        return new ExchangeRateResponse(response, targetCountry);
    }
}
