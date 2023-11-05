package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private final String INVALID_PAYMENT_RANGE = "[ERROR] 구입금액은 로또 개당 가격 이상이어야 합니다.";
    private final String INVALID_PAYMENT_FORM = "[ERROR] 구입금액은 로또 개당 가격으로 나누어 떨어져야 합니다.";

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999})
    @DisplayName("[EXCEPTION]구입금액이 로또 가격보다 작으면 예외가 발생한다.")
    void createPaymentByLessThan1000(int input) {
        int pay = input;

        assertThatThrownBy(() -> new Payment(pay))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PAYMENT_RANGE);
    }

    @ParameterizedTest
    @ValueSource(ints = {1001, 10001, 12345})
    @DisplayName("[EXCEPTION]구입금액이 로또 가격으로 나누어 떨어지지 않으면 예외가 발생한다.")
    void createPaymentByNotDivisible1000(int input) {
        int pay = input;

        assertThatThrownBy(() -> new Payment(pay))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_PAYMENT_FORM);
    }
}