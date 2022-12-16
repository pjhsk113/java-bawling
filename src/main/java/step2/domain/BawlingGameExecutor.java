package step2.domain;

import step2.domain.frame.Frame;
import step2.domain.frame.NormalFrame;
import step2.domain.score.NormalScores;
import step2.domain.score.Scores;
import step2.view.InputView;
import step2.view.OutputView;

public class BawlingGameExecutor {
    public void play() {
        Player player = InputView.inputPlayerName();
        OutputView.printFrame(player);

        Frame frame = NormalFrame.init();
        while(isNormalFrame(frame)) {
            frame = normalFrame(frame);
        }

    }

    private boolean isNormalFrame(Frame frame) {
        return frame instanceof NormalFrame;
    }

    private Frame normalFrame(Frame frame) {
        Frame nextFrame = frameView(frame);
        if (nextFrame.getScores().isStrike()) {
            nextFrame = frameView(nextFrame);
        }
        return nextFrame;
    }

    private Frame frameView(Frame frame) {
        Score score = InputView.inputFrameScore(frame.getFrame());
        Scores scores = frame.getScores().next(score);
        Frame nextFrame = frame.next(scores);
        return nextFrame;
    }
}
