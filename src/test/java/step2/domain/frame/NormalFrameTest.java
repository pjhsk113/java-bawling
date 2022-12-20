package step2.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step2.domain.Score;
import step2.domain.score.NormalScores;
import step2.domain.score.Scores;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("다음 프레임 생성 테스트")
    @ParameterizedTest
    @MethodSource("frameProvider")
    void next_frame_init(Frame frame, Scores expected) {
        assertThat(frame.getPrevFrame().getScores()).isEqualTo(expected);
    }

    private static Stream<Arguments> frameProvider() {
        Scores normalScore1 = NormalScores.init().next(Score.from(4));
        Scores normalScore2 = normalScore1.next(Score.from(3));
        Scores normalScore3 = NormalScores.init().next(Score.from(2));
        Scores normalScore4 = normalScore1.next(Score.from(1));

        Frame frame1 = NormalFrame.of(1, normalScore1, null);
        Frame frame2 = frame1.next(normalScore2);
        Frame frame3 = frame2.next(normalScore3);
        Frame frame4 = frame3.next(normalScore4);

        return Stream.of(
                Arguments.of(frame2, normalScore2),
                Arguments.of(frame3, normalScore2),
                Arguments.of(frame4, normalScore4)
        );
    }

}