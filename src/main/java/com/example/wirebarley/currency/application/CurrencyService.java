package com.example.wirebarley.currency.application;

import com.example.wirebarley.context.CurrencyLayerProperties;
import com.example.wirebarley.currency.ExchangeRate;
import com.example.wirebarley.currency.application.dto.ExchangeApiResponse;
import com.example.wirebarley.currency.domain.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final RestTemplate restTemplate;
    private final CurrencyLayerProperties currencyLayerProperties;

    public ExchangeApiResponse findExchangeRate(String sourceCountry, String targetCountry) {
        UriComponents uri =  UriComponentsBuilder.newInstance()
                .path(currencyLayerProperties.getLiveUrl())
                .queryParam("access_key", currencyLayerProperties.getAccessKey())
                .queryParam("source", sourceCountry)
                .queryParam("currencies", targetCountry)
                .queryParam("format", 1)
                .build();
        return restTemplate.getForObject(uri.toUriString(), ExchangeApiResponse.class);
    }

    public Money transfer(ExchangeRate exchangeRate, BigDecimal transferMoney) {
        Money money = exchangeRate.transfer(new Money("USD", transferMoney));
        return money;
    }
}
