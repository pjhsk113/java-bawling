package step2.domain.score;

import step2.domain.Score;

public class NormalScores extends Scores {

    private NormalScores(Score firstScore, Score secondScore) {
        super(firstScore, secondScore);
    }

    public static NormalScores of(Score firstScore, Score secondScore) {
//        validate(firstScore, secondScore);
        return new NormalScores(firstScore, secondScore);
    }

    public static NormalScores init() {
        return new NormalScores(null, null);
    }

    private static void validate(Score firstScore, Score secondScore) {
        if (firstScore.sum(secondScore) > 10) {
            throw new IllegalArgumentException("프레임 별 투구 점수는 10을 넘을 수 없습니다.");
        }
    }

    @Override
    public Scores next(Score score) {
        if (firstScore == null) {
            return of(score, null);
        }

        return of(firstScore, score);
    }

    @Override
    public boolean isFrameOver() {
        return firstScore != null && secondScore != null;
    }
}
