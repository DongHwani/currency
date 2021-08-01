package com.example.wirebarley.currency.interfaces;

import com.example.wirebarley.currency.application.dto.ExchangeApiResponse;
import com.example.wirebarley.currency.interfaces.dto.ExchangeRateResponse;
import com.example.wirebarley.handler.ErrorResource;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExchangeControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @ParameterizedTest
    @CsvSource(value = {"USD:KRW"}, delimiter = ':')
    @DisplayName("환율정보를 조회한다")
    public void findExchangeRateInformation(String source, String target) {
        //Given & When
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .param("sourceCountry", source)
                .param("targetCountry", target)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/exchange")
                .then().log().all()
                .extract();

        //Then
        ExchangeRateResponse exchangeResponse = response.as(ExchangeRateResponse.class);
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(exchangeResponse.getRate()).isNotNull(),
                () -> assertThat(exchangeResponse.getCurrencyRelation()).isEqualTo(target+"/"+source)
        );
    }


    @ParameterizedTest
    @CsvSource(value = {"USDA:KRWEED"}, delimiter = ':')
    @DisplayName("유효하지 않는 통화국가를 선택하였을 때 오류발생")
    public void findExchangeRateInformation_WhenInvalidCountryType(String source, String target) {
        //Given & When
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .param("sourceCountry", source)
                .param("targetCountry", target)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/exchange")
                .then().log().all()
                .extract();

        //Then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}

