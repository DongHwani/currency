package com.example.wirebarley.currency.domain;

import com.example.wirebarley.currency.ExchangeRate;
import com.example.wirebarley.currency.domain.exception.InvalidNumberMoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ExchangeTest {

    @DisplayName("송금액이 0이거나 0보다 작을 때 송금하였을 경우 예외처리")
    @ParameterizedTest
    @CsvSource(value = {"0:1.5", "-1020:100"}, delimiter = ':')
    public void exchangeInvalid_case1(String amount, String rate){
        assertThatThrownBy(() -> {
            ExchangeRate exchangeRate = buildExchangeRate(rate);
            exchangeRate.transfer(new Money("USD", new BigDecimal(amount)));
        }).isInstanceOf(InvalidNumberMoneyException.class);

    }

    @ParameterizedTest
    @CsvSource(value = {"1000:200", "300:100"}, delimiter = ':')
    public void transfer(String amount, String rate){
        //Given
        ExchangeRate exchangeRate = buildExchangeRate(rate);
        //When
        Money money = exchangeRate.transfer(new Money("USD", new BigDecimal(amount)));

        //Then
        assertThat(money).isEqualTo(new Money(new BigDecimal(amount).multiply(new BigDecimal(rate))));
    }

    private ExchangeRate buildExchangeRate(String rate) {
        return ExchangeRate.builder()
                .sourceCountry("USD")
                .recipientCountry("KRW")
                .rate(new BigDecimal(rate))
                .build();
    }
}
