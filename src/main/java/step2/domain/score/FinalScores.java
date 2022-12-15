package step2.domain.score;

import step2.domain.Score;

public class FinalScores extends Scores {
    private final Score extraScore;

    private FinalScores(Score firstScore, Score secondScore, Score extraScore) {
        super(firstScore, secondScore);
        this.extraScore = extraScore;
    }

    public static FinalScores of(Score firstScore, Score secondScore) {
        return new FinalScores(firstScore, secondScore, null);
    }

    public static FinalScores of(Score firstScore, Score secondScore, Score extraScore) {
        return new FinalScores(firstScore, secondScore, extraScore);
    }

}
