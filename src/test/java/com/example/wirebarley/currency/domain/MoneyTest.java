package com.example.wirebarley.currency.domain;

import com.example.wirebarley.currency.domain.exception.InvalidNumberMoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Money 도메인 테스트")
public class MoneyTest {

    @DisplayName("Dollar 객체 : 1 ~ 10000사이의 금액으로 객체 생성 성공")
    @ParameterizedTest
    @CsvSource(value = {"1.9293:USD", "3000.222:USD", "9999.99:USD"}, delimiter = ':')
    public void createInstanceTest(String amount, String countryType){
        //When
        Money money = Money.getMoney(countryType, amount);

        //Then
        assertAll(
                () -> assertThat(money.getAmount()).isEqualTo(new BigDecimal(amount)),
                () -> assertThat(money).isEqualTo(Money.getMoney(countryType, amount))
        );
    }

    @DisplayName("Dollar 객체 : 10000USD 초과 금액으로 객체 생성시 객체 생성 실패")
    @ParameterizedTest
    @CsvSource(value = {"10001:USD", "20303:USD", "10000.001:USD", "10000.1:USD"}, delimiter = ':')
    public void createInstanceInvalidNumber_WhenDollar(String amount, String countryType){
        assertThatThrownBy(() ->
                Money.getMoney(countryType, amount)
        ).isInstanceOf(InvalidNumberMoneyException.class);
    }

    @DisplayName("0보다 같거나 작은 금액으로 생성했을 경우 예외처리")
    @ParameterizedTest
    @CsvSource(value = {"0:USD", "-21000:USD"}, delimiter = ':')
    public void createInstanceInvalidNumber_case1(String amount, String countryType) {
        assertThatThrownBy(() ->
                Money.getMoney(countryType, amount)
        ).isInstanceOf(InvalidNumberMoneyException.class);
    }

    @DisplayName("금액 곱하기 결과")
    @ParameterizedTest
    @CsvSource(value = {"5000:3000", "2315.23:4500", "1312.00:100"}, delimiter = ':')
    public void multiplyTest(String amount1, String amount2) {
        //Given
        Money money = new Money(new BigDecimal(amount1));

        //When
        Money expected = money.multiply(new BigDecimal(amount2));

        //Then
        assertAll(
                () -> assertThat(money).isNotEqualTo(expected),
                () -> assertThat(expected.getAmount()).isEqualTo(new BigDecimal(amount1).multiply(new BigDecimal(amount2)))
        );
    }

}
