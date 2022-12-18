package step2.domain.frame;

import step2.domain.score.Scores;

public class FinalFrame extends Frame {

    private FinalFrame(int frame, Scores scores, Frame prevFrame) {
        super(frame, scores, prevFrame);
    }

    public static FinalFrame of(int frame, Scores scores, Frame prevFrame) {
        return new FinalFrame(frame, scores, prevFrame);
    }

    @Override
    public Frame next(Scores scores) {
        return of(frame, scores, prevFrame);
    }
}
