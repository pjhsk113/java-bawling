package step4.domain.score;

import step4.domain.Score;

import java.util.stream.Stream;

public class NormalScores extends Scores {

    private NormalScores(Score firstScore, Score secondScore) {
        super(firstScore, secondScore);
    }

    public static NormalScores of(Score firstScore, Score secondScore) {
        if (secondScore != null) {
            validate(firstScore, secondScore);
        }

        return new NormalScores(firstScore, secondScore);
    }

    public static NormalScores init() {
        return new NormalScores(null, null);
    }

    private static void validate(Score firstScore, Score secondScore) {
        if (firstScore.sum(secondScore).getValue() > 10) {
            throw new IllegalArgumentException("프레임 별 투구 점수는 10을 넘을 수 없습니다.");
        }
    }

    @Override
    public Stream<Score> stream() {
        return Stream.of(firstScore, secondScore);
    }

    @Override
    public Scores next(Score score) {
        if (firstScore == null) {
            return of(score, null);
        }

        return of(firstScore, score);
    }
}
