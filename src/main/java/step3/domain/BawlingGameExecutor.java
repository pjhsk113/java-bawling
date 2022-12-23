package step3.domain;

import step3.domain.frame.Frame;
import step3.domain.frame.Frames;
import step3.domain.frame.NormalFrame;
import step3.domain.score.Scores;
import step3.view.InputView;
import step3.view.OutputView;

import java.util.Optional;

public class BawlingGameExecutor {
    public void play() {
        Player player = InputView.inputPlayerName();
        Frames frames = Frames.init();
        OutputView.printFrame(player, frames);

        Frame frame = NormalFrame.init();
        while(isNormalFrame(frame)) {
            normalFrame(frame, player, frames);
            frame = Optional.ofNullable(frame.getNextFrame())
                    .orElse(frame);
        }
        finalFrame(frame, player, frames);
    }

    private boolean isNormalFrame(Frame frame) {
        return frame instanceof NormalFrame;
    }

    private void normalFrame(Frame frame, Player player, Frames frames) {
        frameView(frame, player, frames);
        if (!frame.getScores().isStrike()) {
            frameView(frame, player, frames);
        }
    }

    private void finalFrame(Frame frame, Player player, Frames frames) {
        frameView(frame, player, frames);
        frameView(frame, player, frames);
        Scores scores = frame.getScores();
        if (isExtraFrame(scores)) {
            frameView(frame, player, frames);
        }
    }

    private boolean isExtraFrame(Scores scores) {
        return scores.isStrike() || scores.isSpared();
    }

    private void frameView(Frame frame, Player player, Frames frames) {
        Scores scores = frame.getScores();
        Score score = InputView.inputFrameScore(frame.getFrame());
        frame.next(scores.next(score));
        OutputView.printFrame(player, frames.addFrames(frame));
    }
}
