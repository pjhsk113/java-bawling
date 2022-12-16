package step2.domain.frame;

import step2.domain.score.Scores;

public class FinalFrame extends Frame {

    private FinalFrame(int frame, Scores scores) {
        super(frame, scores);
    }

    public static FinalFrame of(int frame, Scores scores) {
        return new FinalFrame(frame, scores);
    }

    @Override
    public Frame next(Scores scores) {
        return null;
    }
}
