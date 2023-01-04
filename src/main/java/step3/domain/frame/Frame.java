package step3.domain.frame;

import step3.domain.score.Scores;

import java.util.Objects;
import java.util.stream.Stream;

public abstract class Frame {
    final int frame;
    Scores scores;

    Frame(int frame, Scores scores) {
        this.frame = frame;
        this.scores = scores;
    }

    abstract protected int calculateStrike();
    abstract protected int calculateTwoStrike(int totalScore);
    abstract protected int calculateSpared();
    abstract public void next(Scores scores);
    abstract public Frame getNextFrame();

    public int getFrame() {
        return frame;
    }

    public Scores getScores() {
        return scores;
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
