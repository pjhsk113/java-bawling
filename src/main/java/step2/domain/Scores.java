package step2.domain;

public class Scores {
    private Score firstScore;
    private Score secondScore;

    private Scores(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public static Scores of(Score firstScore, Score secondScore) {
        if (firstScore.isStrike()) {
            return new Scores(firstScore, null);
        }
        validate(firstScore, secondScore);
        return new Scores(firstScore, secondScore);
    }

    private static void validate(Score firstScore, Score secondScore) {
        if (firstScore.sum(secondScore) > 10) {
            throw new IllegalArgumentException("프레임 별 투구 점수는 10을 넘을 수 없습니다.");
        }
    }
}
