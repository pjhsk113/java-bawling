package step3.domain.frame;

import step3.domain.score.Scores;

import java.util.Objects;
import java.util.function.Supplier;
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
        boolean isNotFrameOver = scores == null || !scores.isFrameOver();
        boolean isStrike = !isNotFrameOver && scores.isStrike();
        boolean isSpared = !isNotFrameOver && !isStrike && scores.isSpared();

        return Stream.of(isEmpty(isNotFrameOver, () -> -1),
                        isEmpty(isStrike, this::calculateStrike),
                        isEmpty(isSpared, this::calculateSpared))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(scores::totalScore)
                .get();
    }

    private Supplier<Integer> isEmpty(boolean type, Supplier<Integer> calculator) {
        return type ? calculator : null;
    }
}
