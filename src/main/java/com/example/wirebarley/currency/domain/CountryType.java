package com.example.wirebarley.currency.domain;

import lombok.Getter;

@Getter
public enum CountryType {

    KRW("한국"), USD("미국"), JPY("일본"), PHP("필리핀");

    private final String country;

    CountryType(String country) {
        this.country = country;
    }
}
