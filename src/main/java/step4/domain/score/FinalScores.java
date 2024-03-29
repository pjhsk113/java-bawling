package step4.domain.score;

import step4.domain.Score;

import java.util.stream.Stream;

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

    public static FinalScores of(Score firstScore, Score secondScore, Score bonusScore) {
        return new FinalScores(firstScore, secondScore, bonusScore);
    }

    private static void validate(Score firstScore, Score secondScore) {
        if (!firstScore.isStrike() && firstScore.sum(secondScore).getValue() > 10) {
            throw new IllegalArgumentException("프레임 별 투구 점수는 10을 넘을 수 없습니다.");
        }
    }

    public static FinalScores init() {
        return of(null, null);
    }

    public boolean filledBonus() {
        return extraScore != null;
    }

    @Override
    public Stream<Score> stream() {
        return Stream.of(firstScore, secondScore, extraScore);
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
}
