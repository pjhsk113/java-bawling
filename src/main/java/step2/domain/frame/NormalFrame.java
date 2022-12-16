package step2.domain.frame;

import step2.domain.score.FinalScores;
import step2.domain.score.NormalScores;
import step2.domain.score.Scores;

public class NormalFrame extends Frame {

    private NormalFrame(int frame, Scores scores) {
        super(frame, scores);
    }

    public static NormalFrame of(int frame, Scores scores) {
        return new NormalFrame(frame, scores);
    }

    public static NormalFrame init() {
        return NormalFrame.of(1, NormalScores.init());
    }

    @Override
    public Frame next(Scores scores) {
        if (isFrameNotOver()) {
            return of(frame, scores);
        }

        int nextFrame = frame + 1;

        if (isNormalFrame(nextFrame)) {
            return of(nextFrame, NormalScores.init());
        }

        return FinalFrame.of(nextFrame, FinalScores.init());
    }

    private boolean isFrameNotOver() {
        return !scores.isStrike() && !scores.isFrameOver();
    }

    private boolean isNormalFrame(int frame) {
        return frame < 10;
    }
}
