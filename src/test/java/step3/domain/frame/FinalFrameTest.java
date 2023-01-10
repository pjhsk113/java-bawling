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

}