package step3.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step3.domain.score.FinalScores;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FinalScoresTest {

    @DisplayName("추가 투구가 정상적으로 이루어졌는지 테스트")
    @ParameterizedTest
    @MethodSource("frameOverAndExtraScoreProvider")
    void extra_score_test(FinalScores scores) {
        assertThat(scores.filledBonus()).isTrue();
    }

    private static Stream<Arguments> frameOverAndExtraScoreProvider() {
        return Stream.of(
                Arguments.of(FinalScores.of(null, null, Score.valueOf(1))),
                Arguments.of(FinalScores.of(null, Score.valueOf(2), Score.valueOf(3))),
                Arguments.of(FinalScores.of(Score.valueOf(4), null, Score.valueOf(5))),
                Arguments.of(FinalScores.of(Score.valueOf(6), Score.valueOf(7), Score.valueOf(8)))
        );
    }

}