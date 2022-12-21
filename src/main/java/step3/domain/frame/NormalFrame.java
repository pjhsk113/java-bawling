package step3.domain.frame;

import step3.domain.score.FinalScores;
import step3.domain.score.NormalScores;
import step3.domain.score.Scores;

public class NormalFrame extends Frame {

    private NormalFrame(int frame, Scores scores, Frame prevFrame) {
        super(frame, scores, prevFrame);
    }

    public static NormalFrame of(int frame, Scores scores, Frame prevFrame) {
        return new NormalFrame(frame, scores, prevFrame);
    }

    public static NormalFrame init() {
        return NormalFrame.of(1, NormalScores.init(), null);
    }

    @Override
    public Frame next(Scores scores) {
        Frame currentFrame = of(frame, scores, prevFrame);
        if (isFrameNotOver(scores)) {
            return currentFrame;
        }

        int nextFrame = frame + 1;

        if (isNormalFrame(nextFrame)) {
            return of(nextFrame, NormalScores.init(), currentFrame);
        }

        return FinalFrame.of(nextFrame, FinalScores.init(), currentFrame);
    }

    private boolean isFrameNotOver(Scores scores) {
        return !scores.isStrike() && !scores.isFrameOver();
    }

    private boolean isNormalFrame(int frame) {
        return frame < 10;
    }

    @Override
    protected int calculateStrike() {
        return 0;
    }

    @Override
    protected int calculateSpared() {
        return 0;
    }
}
