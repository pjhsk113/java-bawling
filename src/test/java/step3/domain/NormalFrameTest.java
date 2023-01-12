package step3.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step3.domain.frame.Frame;
import step3.domain.frame.NormalFrame;
import step3.domain.score.NormalScores;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NormalFrameTest {

    @DisplayName("다음 프레임 null 생성 테스트")
    @Test
    void next_frame_null_init() {
        Frame frame = NormalFrame.init();
        frame.next(frame.getScores().next(Score.valueOf(1)));
        assertThat(frame.getNextFrame()).isEqualTo(null);
    }

    @DisplayName("다음 프레임 정상 생성 테스트")
    @ParameterizedTest
    @MethodSource("frameAndNextFrameProvider")
    public void next_frame_init(NormalFrame frame) {
        assertEquals(true, frame.getNextFrame() != null);
    }

    private static Stream<Arguments> frameAndNextFrameProvider() {
        Frame frame1 = NormalFrame.init();
        Frame frame2 = NormalFrame.of(2, NormalScores.init(), null);

        frame1.next(frame1.getScores().next(Score.valueOf(10)));
        frame2.next(frame2.getScores().next(Score.valueOf(1)));
        frame2.next(frame2.getScores().next(Score.valueOf(2)));
        return Stream.of(
                Arguments.of(frame1),
                Arguments.of(frame2)
        );
    }

    @DisplayName("점수 계산 테스트")
    @ParameterizedTest
    @MethodSource("frameAndScoreProvider")
    public void frame_calc(NormalFrame frame, int expected) {
        assertThat(frame.calculateScore()).isEqualTo(expected);
    }

    private static Stream<Arguments> frameAndScoreProvider() {
        return Stream.of(
                Arguments.of(
                        NormalFrame.of(1, NormalScores.of(Score.valueOf(1), Score.valueOf(2)), null),
                        3
                ),
                Arguments.of(
                        NormalFrame.of(1, NormalScores.of(Score.valueOf(0), Score.valueOf(9)), null),
                        9
                ),
                Arguments.of(
                        NormalFrame.of(1, NormalScores.of(Score.valueOf(8), Score.valueOf(0)), null),
                        8
                ),
                Arguments.of(
                        NormalFrame.of(1, NormalScores.of(Score.valueOf(5), Score.valueOf(4)), null),
                        9
                ),
                Arguments.of(
                        NormalFrame.of(1, NormalScores.of(Score.valueOf(0), Score.valueOf(0)), null),
                        0
                )
        );
    }

    @DisplayName("Spared 점수 계산 테스트")
    @ParameterizedTest
    @MethodSource("frameAndSparedScoreProvider")
    public void frame_spared_calc(NormalFrame frame, int expected) {
        assertThat(frame.calculateScore()).isEqualTo(expected);
    }

    private static Stream<Arguments> frameAndSparedScoreProvider() {
        return Stream.of(
                Arguments.of(NormalFrame.of(1, NormalScores.of(Score.valueOf(0), Score.valueOf(10)),
                             NormalFrame.of(2, NormalScores.of(Score.valueOf(5), null), null)),
                        15
                ),
                Arguments.of(NormalFrame.of(1, NormalScores.of(Score.valueOf(9), Score.valueOf(1)),
                             NormalFrame.of(2, NormalScores.of(Score.valueOf(10), null), null)),
                        20
                ),
                Arguments.of(NormalFrame.of(1, NormalScores.of(Score.valueOf(5), Score.valueOf(5)),
                             NormalFrame.of(2, NormalScores.of(Score.valueOf(0), null), null)),
                        10
                ),
                Arguments.of(NormalFrame.of(1, NormalScores.of(Score.valueOf(4), Score.valueOf(6)),
                             NormalFrame.of(2, NormalScores.init(), null)),
                        -1
                )
        );
    }

}