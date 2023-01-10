package step3.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step3.domain.Score;
import step3.domain.score.FinalScores;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FinalFrameTest {

    @DisplayName("마지막 프레임의 다음 프레임이 null인지 테스트.")
    @ParameterizedTest
    @MethodSource("finalFrameAndNextFrameProvider")
    void final_frame_next_frame_init_test(Frame frame, Frame nextFrame) {
        assertEquals(nextFrame, frame.getNextFrame());
    }

    private static Stream<Arguments> finalFrameAndNextFrameProvider() {
        Frame finalFrame = FinalFrame.of(10, FinalScores.init());
        finalFrame.next(finalFrame.getScores().next(Score.valueOf(1)));
        Frame nextFrame = finalFrame.getNextFrame();
        return Stream.of(
                Arguments.of(finalFrame, null),
                Arguments.of(finalFrame, nextFrame)
        );
    }

    @DisplayName("Miss 점수 계산 테스트")
    @ParameterizedTest
    @MethodSource("provideFrameAndMissScore")
    public void Miss_계산_테스트(FinalFrame frame, int expected) {
        assertEquals(expected, frame.calculateScore());
    }

    private static Stream<Arguments> provideFrameAndMissScore() {
        return Stream.of(
                Arguments.of(
                        FinalFrame.of(10, FinalScores.of(Score.valueOf(1), Score.valueOf(2))),
                        3
                ),
                Arguments.of(
                        FinalFrame.of(10, FinalScores.of(Score.valueOf(0), Score.valueOf(9))),
                        9
                ),
                Arguments.of(
                        FinalFrame.of(10, FinalScores.of(Score.valueOf(8), Score.valueOf(0))),
                        8
                ),
                Arguments.of(
                        FinalFrame.of(10, FinalScores.of(Score.valueOf(5), Score.valueOf(4))),
                        9
                ),
                Arguments.of(
                        FinalFrame.of(10, FinalScores.of(Score.valueOf(0), Score.valueOf(0))),
                        0
                )
        );
    }

    @DisplayName("Spared 점수 계산 테스트")
    @ParameterizedTest
    @MethodSource("provideFrameAndSparedScore")
    public void Spared_계산_테스트(FinalFrame frame, int expected) {
        assertEquals(expected, frame.calculateScore());
    }

    private static Stream<Arguments> provideFrameAndSparedScore() {
        return Stream.of(
                Arguments.of(
                        FinalFrame.of(10, FinalScores.of(Score.valueOf(0), Score.valueOf(10), Score.valueOf(5))),
                        15
                ),
                Arguments.of(
                        FinalFrame.of(10, FinalScores.of(Score.valueOf(9), Score.valueOf(1), Score.valueOf(10))),
                        20
                ),
                Arguments.of(
                        FinalFrame.of(10, FinalScores.of(Score.valueOf(5), Score.valueOf(5), Score.valueOf(0))),
                        10
                ),
                Arguments.of(
                        FinalFrame.of(10, FinalScores.of(Score.valueOf(4), Score.valueOf(6), Score.valueOf(7))),
                        17
                ),
                Arguments.of(
                        FinalFrame.of(10, FinalScores.of(Score.valueOf(4), Score.valueOf(6), null)),
                        -1
                )
        );
    }

}