package step3.domain.frame;


import step3.domain.score.Scores;

public class FinalFrame extends Frame {

    private FinalFrame(int frame, Scores scores) {
        super(frame, scores);
    }

    public static FinalFrame of(int frame, Scores scores) {
        return new FinalFrame(frame, scores);
    }

    @Override
    public void next(Scores scores) {
        this.scores = scores;
    }

    @Override
    public Frame getNextFrame() {
        return null;
    }

    @Override
    protected int calculateStrike() {
        return 0;
    }

    @Override
    protected int calculateTwoStrike(int totalScore) {
        return 0;
    }

    @Override
    protected int calculateSpared() {
        return 0;
    }
}
