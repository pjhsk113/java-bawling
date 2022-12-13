package step2.domain.score;

import step2.domain.Score;
import step2.domain.score.Scores;

public class NormalScores extends Scores {

    private NormalScores(Score firstScore, Score secondScore) {
        super(firstScore, secondScore);
    }

    public static NormalScores of(Score firstScore, Score secondScore) {
        validate(firstScore, secondScore);
        return new NormalScores(firstScore, secondScore);
    }
}
