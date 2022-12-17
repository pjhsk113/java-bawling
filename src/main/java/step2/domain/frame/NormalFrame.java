package step2.domain.frame;

import step2.domain.score.FinalScores;
import step2.domain.score.NormalScores;
import step2.domain.score.Scores;

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
}
