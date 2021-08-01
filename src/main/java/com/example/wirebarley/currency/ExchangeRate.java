package com.example.wirebarley.currency;

import com.example.wirebarley.currency.domain.Dollar;
import com.example.wirebarley.currency.domain.Money;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExchangeRate {

    private String sourceCountry;
    private String recipientCountry;
    private BigDecimal rate;

    public Money transfer(Money transferAmount) {
        Money transferMoney = new Money(recipientCountry, transferAmount.getAmount());
        return transferMoney.multiply(rate);
    }


}
