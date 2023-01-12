package step3.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step3.domain.score.NormalScores;
import step3.domain.score.Scores;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class NormalScoresTest {

    @DisplayName("스코어 스트라이크 테스트")
    @Test
    void strike_test() {
        Scores scores = NormalScores.init().next(Score.valueOf(10));
        assertThat(scores.isStrike()).isTrue();
    }

    @DisplayName("스코어의 투구 횟수가 마무리 됐는지 확인 테스트")
    @ParameterizedTest
    @MethodSource("frameOverScoresProvider")
    void scores_frame_over_test(NormalScores scores) {
        assertThat(scores.isFrameOver()).isTrue();
    }

    private static Stream<Arguments> frameOverScoresProvider() {
        return Stream.of(
                Arguments.of(
                        NormalScores.init()
                                .next(Score.valueOf(1))
                                .next(Score.valueOf(2))
                ),
                Arguments.of(
                        NormalScores.init()
                                .next(Score.valueOf(3))
                                .next(Score.valueOf(4))
                ),
                Arguments.of(
                        NormalScores.init()
                                .next(Score.valueOf(5))
                                .next(Score.valueOf(5))
                ),
                Arguments.of(
                        NormalScores.init()
                                .next(Score.valueOf(10))
                ),
                Arguments.of(
                        NormalScores.init()
                                .next(Score.valueOf(0))
                                .next(Score.valueOf(10))
                )
        );
    }

    @DisplayName("스페어 처리 테스트")
    @ParameterizedTest
    @MethodSource("sparedScoresProvider")
    void scores_spared_test(NormalScores scores) {
        assertThat(scores.isSpared()).isTrue();
    }

    private static Stream<Arguments> sparedScoresProvider() {
        return Stream.of(
                Arguments.of(
                        NormalScores.init()
                                .next(Score.valueOf(1))
                                .next(Score.valueOf(9))
                ),
                Arguments.of(
                        NormalScores.init()
                                .next(Score.valueOf(2))
                                .next(Score.valueOf(8))
                ),
                Arguments.of(
                        NormalScores.init()
                                .next(Score.valueOf(3))
                                .next(Score.valueOf(7))
                ),
                Arguments.of(
                        NormalScores.init()
                                .next(Score.valueOf(4))
                                .next(Score.valueOf(6))
                ),
                Arguments.of(
                        NormalScores.init()
                                .next(Score.valueOf(5))
                                .next(Score.valueOf(5))
                )
        );
    }

    @DisplayName("스코어 합계 테스트")
    @ParameterizedTest
    @MethodSource("scoresSumProvider")
    void scores_total_score_test(NormalScores scores, int expected) {
        assertThat(scores.totalScore()).isEqualTo(expected);
    }

    private static Stream<Arguments> scoresSumProvider() {
        return Stream.of(
                Arguments.of(
                        NormalScores.init()
                                .next(Score.valueOf(1)),
                        1
                ),
                Arguments.of(
                        NormalScores.init()
                                .next(Score.valueOf(2))
                                .next(Score.valueOf(8)),
                        10
                ),
                Arguments.of(
                        NormalScores.init()
                                .next(Score.valueOf(0))
                                .next(Score.valueOf(7)),
                        7
                ),
                Arguments.of(
                        NormalScores.init()
                                .next(Score.valueOf(0))
                                .next(Score.valueOf(0)),
                        0
                )
        );
    }

}