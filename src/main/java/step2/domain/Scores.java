package step2.domain;

import java.util.List;

public class Scores {
    private Score firstScore;
    private Score secondScore;

    private Scores(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public static Scores of(Score firstScore, Score secondScore) {
        return new Scores(firstScore, secondScore);
    }
}
