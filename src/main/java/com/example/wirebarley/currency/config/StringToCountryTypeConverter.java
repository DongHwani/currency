package com.example.wirebarley.currency.config;

import com.example.wirebarley.currency.domain.CountryType;
import org.springframework.core.convert.converter.Converter;

public class StringToCountryTypeConverter implements Converter<String, CountryType> {

    @Override
    public CountryType convert(String source) {
        return CountryType.valueOf(source);
    }
}
