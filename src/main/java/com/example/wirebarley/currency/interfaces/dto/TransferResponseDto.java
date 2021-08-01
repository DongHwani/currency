package com.example.wirebarley.currency.interfaces.dto;

import com.example.wirebarley.currency.application.dto.ExchangeApiResponse;
import com.example.wirebarley.currency.domain.Money;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransferResponseDto {

    private static final DecimalFormat DECIMAL_EXPRESSION = new DecimalFormat("#,##0.00");

    private BigDecimal recipientRate;
    private String recipientRateExpression;
    private String recipientCountry;

    private TransferResponseDto(Money money) {
        this.recipientRate = money.getAmount();
        this.recipientRateExpression = DECIMAL_EXPRESSION.format(recipientRate);
        this.recipientCountry = money.getCountryType();
    }

    public static TransferResponseDto of(Money money) {
        return new TransferResponseDto(money);
    }
}
