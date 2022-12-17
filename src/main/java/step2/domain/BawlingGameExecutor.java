package step2.domain;

import step2.domain.frame.Frame;
import step2.domain.frame.Frames;
import step2.domain.frame.NormalFrame;
import step2.domain.score.Scores;
import step2.view.InputView;
import step2.view.OutputView;

public class BawlingGameExecutor {
    public void play() {
        Player player = InputView.inputPlayerName();
        Frames frames = Frames.init();
        OutputView.printFrame(player, frames);

        Frame frame = NormalFrame.init();
        while(isNormalFrame(frame)) {
            frame = normalFrame(frame, player, frames);
        }
        finalFrame(frame, player, frames);
    }

    private boolean isNormalFrame(Frame frame) {
        return frame instanceof NormalFrame;
    }

    private Frame normalFrame(Frame frame, Player player, Frames frames) {
        Frame nextFrame = frameView(frame, player, frames);
        if (nextFrame.getScores().isStrike()) {
            nextFrame = frameView(nextFrame, player, frames);
        }
        return nextFrame;
    }

    private void finalFrame(Frame frame, Player player, Frames frames) {
        Frame nextFrame = frameView(frame, player, frames);
        nextFrame = frameView(nextFrame, player, frames);

        if (isExtraFrame(nextFrame)) {
            frameView(nextFrame, player, frames);
        }
    }

    private boolean isExtraFrame(Frame frame) {
        return frame.getScores().isStrike() || frame.getScores().isSpared();
    }

    private Frame frameView(Frame frame, Player player, Frames frames) {
        Score score = InputView.inputFrameScore(frame.getFrame());
        Scores scores = frame.getScores().next(score);
        Frame nextFrame = frame.next(scores);
        OutputView.printFrame(player, frames.addFrames(nextFrame));
        return nextFrame;
    }
}
