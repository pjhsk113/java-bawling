package step3.domain.frame;

import step3.domain.score.Scores;

import java.util.Objects;
import java.util.stream.Stream;

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
    abstract protected int calculateStrike();
    abstract protected int calculateSpared();

    public int getFrame() {
        return frame;
    }

    public Scores getScores() {
        return scores;
    }

    public Frame getPrevFrame() {
        return prevFrame;
    }

    public int calculateScore() {
        return Stream.of(scores.isFrameOver() ? -1 : null,
                        scores.isStrike() ? calculateStrike() : null,
                        scores.isSpared() ? calculateSpared() : null)
                .filter(Objects::nonNull)
                .findFirst()
                .orElseGet(scores::totalScore);
    }
}
