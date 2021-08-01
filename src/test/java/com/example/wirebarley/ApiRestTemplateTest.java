package com.example.wirebarley;

import com.example.wirebarley.context.CurrencyLayerProperties;
import com.example.wirebarley.currency.application.dto.ExchangeApiResponse;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@SpringBootTest
public class ApiRestTemplateTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CurrencyLayerProperties currencyLayerProperties;

    @Autowired
    private Gson gson;


    @DisplayName("USD -> KRW 환율조회 api를 확인하는 테스트")
    @ParameterizedTest
    @CsvSource(value = {"USD:KRW"}, delimiter = ':')
    public void currencyApiTest(String source, String target){

        //Given
        UriComponents uri = buildAccessKeyParameter(source, target);

        //When
        ExchangeApiResponse expected = restTemplate.getForObject(uri.toUriString(), ExchangeApiResponse.class);

        //Then
        assertAll(
                () -> assertThat(expected.isSuccess()).isTrue(),
                () -> assertThat(expected.getSource()).isEqualTo(source),
                () -> assertThat(expected.findExchangeRate(source + target)).isNotNull()
        );


    }

    private UriComponents buildAccessKeyParameter(String source, String target) {
        return UriComponentsBuilder.newInstance()
                .path(currencyLayerProperties.getLiveUrl())
                .queryParam("access_key", currencyLayerProperties.getAccessKey())
                .queryParam("source", source)
                .queryParam("currencies", target)
                .queryParam("format", 1)
                .build();
    }
}
