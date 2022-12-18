package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ScoreTest {

    @DisplayName("0 ~ 10 이상의 값이 점수로 들어오는 경우 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void score_init_test(int score) {
        assertThatThrownBy(() -> Score.from(score))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스트라이크 테스트")
    @Test
    void strike_test() {
        Score score = Score.from(10);
        assertThat(score.isStrike()).isTrue();
    }

    @DisplayName("스페어 테스트")
    @Test
    void spared_test() {
        Score score1 = Score.from(1);
        Score score2 = Score.from(9);
        assertThat(score1.isSpare(score2)).isTrue();
    }

    @DisplayName("거터 테스트")
    @Test
    void gutter_test() {
        Score score1 = Score.from(0);
        assertThat(score1.isGutter()).isTrue();
    }
}