package com.example.wirebarley.currency.domain.exception;

public class InvalidNumberMoneyException extends RuntimeException {

    private static final String MESSAGE = "송금액이 바르지 않습니다.";

    public InvalidNumberMoneyException() {
        super(MESSAGE);
    }

    public InvalidNumberMoneyException(String message) {
        super(message);
    }
}
