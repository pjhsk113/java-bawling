package step3.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step3.domain.frame.FinalFrame;
import step3.domain.frame.Frame;
import step3.domain.frame.Frames;
import step3.domain.frame.NormalFrame;
import step3.domain.score.FinalScores;
import step3.domain.score.NormalScores;
import step3.domain.score.Scores;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("프레임의 갯수가 10개인지 확인")
    @Test
    void frame_count_test() {
        Frame firstFrame = NormalFrame.init();
        assertThat(Frames.frameInfo(firstFrame).count()).isEqualTo(10);
    }

    @DisplayName("전체 프레임이 정상적으로 생성되는지 확인")
    @ParameterizedTest
    @MethodSource("framesProvider")
    void all_frame_init_test(Frame firstFrame, long expected) {
        assertThat(Frames.addFrames(firstFrame).size()).isEqualTo(expected);
    }

    private static Stream<Arguments> framesProvider() {
        Scores normalScores = NormalScores.init();
        Scores finalScores = FinalScores.init();
        Frame frame1 = NormalFrame.init();
        Frame frame2 = NormalFrame.of(2, normalScores, frame1);
        Frame frame3 = NormalFrame.of(3, normalScores, frame2);
        Frame frame4 = NormalFrame.of(4, normalScores, frame3);
        Frame frame5 = NormalFrame.of(5, normalScores, frame4);
        Frame frame6 = NormalFrame.of(6, normalScores, frame5);
        Frame frame7 = NormalFrame.of(7, normalScores, frame6);
        Frame frame8 = NormalFrame.of(8, normalScores, frame7);
        Frame frame9 = NormalFrame.of(9, normalScores, frame8);
        Frame frame10 = FinalFrame.of(10, finalScores);
        return Stream.of(
                Arguments.of(frame1, 1),
                Arguments.of(frame2, 2),
                Arguments.of(frame3, 3),
                Arguments.of(frame4, 4),
                Arguments.of(frame5, 5),
                Arguments.of(frame6, 6),
                Arguments.of(frame7, 7),
                Arguments.of(frame8, 8),
                Arguments.of(frame9, 9),
                Arguments.of(frame10, 1)
        );
    }
}