package step3.domain;

import step3.domain.frame.Frame;
import step3.domain.frame.NormalFrame;
import step3.domain.score.Scores;
import step3.view.InputView;
import step3.view.OutputView;

import java.util.Optional;

public class BawlingGameExecutor {
    public void play() {
        Player player = InputView.inputPlayerName();
        Frame frame = NormalFrame.init();
        PlayerFrames playerFrames = PlayerFrames.of(player, frame);

        OutputView.printFrame(playerFrames);
        while(isNormalFrame(frame)) {
            normalFrame(frame, playerFrames);
            frame = Optional.ofNullable(frame.getNextFrame())
                    .orElse(frame);
        }
        finalFrame(frame, playerFrames);
    }

    private boolean isNormalFrame(Frame frame) {
        return frame instanceof NormalFrame;
    }

    private void normalFrame(Frame frame, PlayerFrames playerFrames) {
        frameView(frame, playerFrames);
        if (!frame.getScores().isStrike()) {
            frameView(frame, playerFrames);
        }
    }

    private void finalFrame(Frame frame, PlayerFrames playerFrames) {
        frameView(frame, playerFrames);
        frameView(frame, playerFrames);
        Scores scores = frame.getScores();
        if (isExtraFrame(scores)) {
            frameView(frame, playerFrames);
        }
    }

    private boolean isExtraFrame(Scores scores) {
        return scores.isStrike() || scores.isSpared();
    }

    private void frameView(Frame frame, PlayerFrames playerFrames) {
        Scores scores = frame.getScores();
        Score score = InputView.inputFrameScore(frame.getFrame());
        frame.next(scores.next(score));
        OutputView.printFrame(playerFrames);
    }
}
