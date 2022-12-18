package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @DisplayName("Player 이름에 공백이나 3글자 이상이 입력되면 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"aaaa", "bbbbb", ""})
    void player_init_test(String name) {
        assertThatThrownBy(() -> Player.from(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}