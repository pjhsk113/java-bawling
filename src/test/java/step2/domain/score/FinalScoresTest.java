package step2.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step2.domain.Score;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FinalScoresTest {

    @DisplayName("Scores가 정상적으로 채워지는지 테스트")
    @ParameterizedTest
    @MethodSource("scoresProvider")
    void scores_frame_over_test(FinalScores scores) {
        assertThat(scores.isFrameOver()).isTrue();
    }

    private static Stream<Arguments> scoresProvider() {
        return Stream.of(
                Arguments.of(
                        FinalScores.init()
                                .next(Score.from(4))
                                .next(Score.from(4))
                                .next(Score.from(4))
                ),
                Arguments.of(
                        FinalScores.init()
                                .next(Score.from(10))
                                .next(Score.from(2))
                                .next(Score.from(3))
                ),
                Arguments.of(
                        FinalScores.init()
                                .next(Score.from(5))
                                .next(Score.from(5))
                                .next(Score.from(5))
                )
        );
    }

    @DisplayName("스페어 처리 테스트")
    @ParameterizedTest
    @MethodSource("scoresSparedProvider")
    void scores_spared_test(FinalScores scores) {
        assertThat(scores.isSpared()).isTrue();
    }

    private static Stream<Arguments> scoresSparedProvider() {
        return Stream.of(
                Arguments.of(
                        FinalScores.init()
                                .next(Score.from(4))
                                .next(Score.from(6))
                ),
                Arguments.of(
                        FinalScores.init()
                                .next(Score.from(1))
                                .next(Score.from(9))
                ),
                Arguments.of(
                        FinalScores.init()
                                .next(Score.from(5))
                                .next(Score.from(5))
                ),
                Arguments.of(
                        FinalScores.init()
                                .next(Score.from(1))
                                .next(Score.from(9))
                                .next(Score.from(5))
                ),
                Arguments.of(
                        FinalScores.init()
                                .next(Score.from(1))
                                .next(Score.from(9))
                                .next(Score.from(1))
                ),
                Arguments.of(
                        FinalScores.init()
                                .next(Score.from(3))
                                .next(Score.from(7))
                                .next(Score.from(2))
                )
        );
    }

    @DisplayName("첫 번째 투구 점수가 스트라이크가 아니고 점수 합산이 10점 이상이면 예외 발생")
    @ParameterizedTest
    @MethodSource("scoresOverProvider")
    void scores_init_test(Score score1, Score score2) {
        assertThatThrownBy(() -> FinalScores.of(score1, score2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> scoresOverProvider() {
        return Stream.of(
                Arguments.of(
                        Score.from(4),
                        Score.from(7)
                ),
                Arguments.of(
                        Score.from(5),
                        Score.from(9)
                ),
                Arguments.of(
                        Score.from(5),
                        Score.from(7)
                )
        );
    }
}