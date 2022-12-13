package step2.domain.score;

import step2.domain.Score;

public abstract class Scores {
    private final Score firstScore;
    private final Score secondScore;

    Scores(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    static void validate(Score firstScore, Score secondScore) {
        if (firstScore.sum(secondScore) > 10) {
            throw new IllegalArgumentException("프레임 별 투구 점수는 10을 넘을 수 없습니다.");
        }
    }
}
