package com.example.wirebarley.currency.domain;

import com.example.wirebarley.currency.domain.exception.InvalidNumberMoneyException;

import java.math.BigDecimal;

public class Dollar extends Money{

    private static final BigDecimal MAX_AMOUNT = new BigDecimal("10000");

    public Dollar(BigDecimal amount) {
        super(amount);
        if(MAX_AMOUNT.compareTo(amount) == -1) {
            throw new InvalidNumberMoneyException("송금액이 바르지 않습니다.");
        }
    }
}
