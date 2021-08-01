package com.example.wirebarley.handler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResource {

    private int code;
    private String message;

    public ErrorResource(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResource(String message) {
        this.message = message;
    }
}
