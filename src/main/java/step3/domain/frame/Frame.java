package step3.domain.frame;

import step3.domain.score.Scores;

public abstract class Frame {
    final int frame;
    final Scores scores;
    final Frame prevFrame;

    Frame(int frame, Scores scores, Frame prevFrame) {
        this.frame = frame;
        this.scores = scores;
        this.prevFrame = prevFrame;
    }

    abstract public Frame next(Scores scores);

    public int getFrame() {
        return frame;
    }

    public Scores getScores() {
        return scores;
    }

    public Frame getPrevFrame() {
        return prevFrame;
    }
}
