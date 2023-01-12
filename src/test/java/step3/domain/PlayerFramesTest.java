package step3.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step3.domain.frame.NormalFrame;
import step3.domain.score.NormalScores;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerFramesTest {

    @DisplayName("10개의 프레임 출력 테스트")
    @Test
    public void frame_info_test() {
        assertEquals(10,
                PlayerFrames.of(Player.from("pjh"), NormalFrame.of(1, NormalScores.init(),null))
                        .getFrameInfo()
                        .count()
        );
    }

}