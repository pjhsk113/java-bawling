package step4.domain.score;

import step4.domain.Score;

import java.util.stream.Stream;

public abstract class Scores {
    final Score firstScore;
    final Score secondScore;

    Scores(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    abstract public Scores next(Score score);
    abstract public Stream<Score> stream();

    public boolean isStrike() {
        return firstScore != null && firstScore.isStrike();
    }

    public boolean isSpared() {
        return firstScore.isSpare(secondScore);
    }

    public boolean isEmpty() {
        return firstScore == null && secondScore == null;
    }

    public int totalScore() {
        return firstScore.sum(secondScore).getValue();
    }

    public boolean isFrameOver() {
        return (firstScore != null && secondScore != null) || isStrike();
    }
}
