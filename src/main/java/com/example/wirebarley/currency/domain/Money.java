package com.example.wirebarley.currency.domain;

import com.example.wirebarley.currency.domain.exception.InvalidNumberMoneyException;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    private static final BigDecimal MAX_AMOUNT_USD = new BigDecimal("10000");
    private static final int POSITIVE_AMOUNT = 1;

    private BigDecimal amount;
    private String countryType;

    public static Money getMoney(String countryType, String amount) {
        Money money = null;
        switch (countryType) {
            case "USD":
                money = new Dollar(new BigDecimal(amount));
                break;
            default:
                money = new Money(countryType, new BigDecimal(amount));
        }
        return money;
    }
    public Money(String countryType, BigDecimal amount){
        if(amount.signum() != POSITIVE_AMOUNT) {
            throw new InvalidNumberMoneyException();
        }
        if(countryType == "USD"){
            if(MAX_AMOUNT_USD.compareTo(amount) == -1) {
                throw new InvalidNumberMoneyException("송금액이 바르지 않습니다.");
            }
        }

        this.amount = amount;
        this.countryType = countryType;
    }

    public Money(BigDecimal amount) {
        if(amount.signum() != POSITIVE_AMOUNT) {
            throw new InvalidNumberMoneyException();
        }
        this.amount = amount;
    }

    public Money multiply(BigDecimal targetAmount) {
        return new Money(countryType, amount.multiply(targetAmount));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCountryType() {
        return countryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }


}
