package step3.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScoreTest {

    @DisplayName("스코어 합계 테스트")
    @ParameterizedTest
    @MethodSource("scoreSumProvider")
    void score_sum_test(Score score1, Score score2, Score expected) {
        assertEquals(expected, score1.sum(score2));
    }

    private static Stream<Arguments> scoreSumProvider() {
        return Stream.of(
                Arguments.of(Score.valueOf(0), Score.valueOf(0), Score.valueOf(0)),
                Arguments.of(Score.valueOf(1), Score.valueOf(2), Score.valueOf(3)),
                Arguments.of(Score.valueOf(1), Score.valueOf(6), Score.valueOf(7)),
                Arguments.of(Score.valueOf(3), Score.valueOf(2), Score.valueOf(5)),
                Arguments.of(Score.valueOf(7), Score.valueOf(2), Score.valueOf(9)),
                Arguments.of(Score.valueOf(8), Score.valueOf(1), Score.valueOf(9)),
                Arguments.of(Score.valueOf(0), Score.valueOf(10), Score.valueOf(10)),
                Arguments.of(Score.valueOf(10), Score.valueOf(0), Score.valueOf(10))
        );
    }

}