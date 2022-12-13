package step2.domain.frame;

import step2.domain.score.Scores;

public class NormalFrame extends Frame {

    private NormalFrame(int frame, Scores scores) {
        super(frame, scores);
    }

    public static NormalFrame of(int frame, Scores scores) {
        return new NormalFrame(frame, scores);
    }
}
