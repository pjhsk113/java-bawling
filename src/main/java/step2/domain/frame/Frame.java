package step2.domain.frame;

import step2.domain.score.Scores;

public abstract class Frame {
    final int frame;
    final Scores scores;

    Frame(int frame, Scores scores) {
        this.frame = frame;
        this.scores = scores;
    }
}
