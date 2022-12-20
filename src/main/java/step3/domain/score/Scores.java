package step3.domain.score;

import step3.domain.Score;

import java.util.stream.Stream;

public abstract class Scores {
    final Score firstScore;
    final Score secondScore;

    Scores(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    abstract public Scores next(Score score);
    abstract public boolean isFrameOver();

    public boolean isStrike() {
        return firstScore != null && firstScore.isStrike();
    }

    public boolean isSpared() {
        return firstScore.isSpare(secondScore);
    }

    public Stream<Score> stream() {
        return Stream.of(firstScore, secondScore);
    }
}
