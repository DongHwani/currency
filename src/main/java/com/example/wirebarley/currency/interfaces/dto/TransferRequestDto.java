package com.example.wirebarley.currency.interfaces.dto;

import com.example.wirebarley.currency.ExchangeRate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransferRequestDto {

    private String source;
    private String target;
    private String rate;
    private BigDecimal transferMoney;

    public ExchangeRate of(){
        return ExchangeRate.builder()
                .sourceCountry(source)
                .recipientCountry(target)
                .rate(new BigDecimal(rate))
                .build();
    }
}
