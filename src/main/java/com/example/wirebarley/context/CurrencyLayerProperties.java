package com.example.wirebarley.context;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "currencylayer")
public class CurrencyLayerProperties {

    private String accessKey;
    private String baseUrl;
    private String liveUrl;
    private String sourceDefaultCurrency;
    private String targetDefaultCurrency;

}
