package step2.domain.score;

import step2.domain.Score;

public class FinalScores extends Scores {
    private final Score extraScore;

    private FinalScores(Score firstScore, Score secondScore, Score extraScore) {
        super(firstScore, secondScore);
        this.extraScore = extraScore;
    }

    public static FinalScores of(Score firstScore, Score secondScore) {
        if (secondScore != null) {
            validate(firstScore, secondScore);
        }

        return new FinalScores(firstScore, secondScore, null);
    }

    private static void validate(Score firstScore, Score secondScore) {
        if (!firstScore.isStrike() && firstScore.sum(secondScore) > 10) {
            throw new IllegalArgumentException("프레임 별 투구 점수는 10을 넘을 수 없습니다.");
        }
    }

    public static FinalScores init() {
        return of(null, null);
    }

    @Override
    public Scores next(Score score) {
        if (firstScore == null) {
            return of(score, null);
        }

        if (secondScore == null) {
            return of(firstScore, score);
        }

        return new FinalScores(firstScore, secondScore, score);
    }

    @Override
    public boolean isFrameOver() {
        return firstScore != null && secondScore != null && extraScore != null;
    }
}
