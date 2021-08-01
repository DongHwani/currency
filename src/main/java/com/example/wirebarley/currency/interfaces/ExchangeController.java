package com.example.wirebarley.currency.interfaces;

import com.example.wirebarley.context.CurrencyLayerProperties;
import com.example.wirebarley.currency.application.CurrencyService;
import com.example.wirebarley.currency.application.dto.ExchangeApiResponse;
import com.example.wirebarley.currency.domain.Money;
import com.example.wirebarley.currency.interfaces.dto.ExchangeRateResponse;
import com.example.wirebarley.currency.interfaces.dto.TransferRequestDto;
import com.example.wirebarley.currency.interfaces.dto.TransferResponseDto;
import com.example.wirebarley.currency.interfaces.exception.ExchangeApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class ExchangeController {

    private final CurrencyService currencyService;
    private final CurrencyLayerProperties currencyLayerProperties;

    @GetMapping("/")
    public String navigateMain(Model model) {
        ExchangeApiResponse response = currencyService.findExchangeRate(currencyLayerProperties.getSourceDefaultCurrency(), currencyLayerProperties.getTargetDefaultCurrency());
        if(response.hasError()) {
            throw new ExchangeApiException(response.getError());
        }
        model.addAttribute("exchangeRate", ExchangeRateResponse.of(response, currencyLayerProperties.getTargetDefaultCurrency()));
        return "/exchange-rate";
    }

    @GetMapping("/exchange")
    public ResponseEntity findExchangeInformation(@RequestParam String sourceCountry, @RequestParam String targetCountry) {
        ExchangeApiResponse response = currencyService.findExchangeRate(sourceCountry, targetCountry);
        if(response.hasError()) {
            throw new ExchangeApiException(response.getError());
        }
        return ResponseEntity.ok(ExchangeRateResponse.of(response, targetCountry));
    }

    @PostMapping("/exchange/transfer")
    public ResponseEntity transfer(@RequestBody TransferRequestDto transferRequestDto){
        Money money = currencyService.transfer(transferRequestDto.of(), transferRequestDto.getTransferMoney());
        return ResponseEntity.ok(TransferResponseDto.of(money));
    }
}
