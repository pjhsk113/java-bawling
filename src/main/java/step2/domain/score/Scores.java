package step2.domain.score;

import step2.domain.Score;
import step2.domain.ScoreType;

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
        return firstScore.isStrike();
    }

    public boolean isSpared() {
        return firstScore.isSpare(secondScore);
    }
}
