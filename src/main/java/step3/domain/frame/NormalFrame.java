package step3.domain.frame;

import step3.domain.score.FinalScores;
import step3.domain.score.NormalScores;
import step3.domain.score.Scores;

public class NormalFrame extends Frame {
    private Frame nextFrame;

    private NormalFrame(int frame, Scores scores, Frame nextFrame) {
        super(frame, scores);
        this.nextFrame = nextFrame;
    }

    public static NormalFrame of(int frame, Scores scores, Frame nextFrame) {
        return new NormalFrame(frame, scores, nextFrame);
    }

    public static NormalFrame init() {
        return NormalFrame.of(1, NormalScores.init(), null);
    }

    @Override
    public void next(Scores scores) {
        this.scores = scores;
        if (isFrameNotOver(scores)) {
            return ;
        }

        int nextFrame = frame + 1;

        if (isNormalFrame(nextFrame)) {
            of(nextFrame, NormalScores.init(), null);
        }

        FinalFrame.of(nextFrame, FinalScores.init());
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

    @Override
    public Frame getNextFrame() {
        return nextFrame;
    }
}
