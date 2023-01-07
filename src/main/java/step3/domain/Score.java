package step3.domain;

import java.util.HashMap;
import java.util.Map;

public class Score {
    private static final int MAX_SCORE = 10;
    private static final int MIN_SCORE = 0;

    private static final Map<Integer, Score> FACTORY = new HashMap<>();
    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score from(int score) {
        validate(score);
        return new Score(score);
    }

    public static Score valueOf(int score) {
        validate(score);
        return FACTORY.computeIfAbsent(score, Score::new);
    }

    private static void validate(int score) {
        if (score > MAX_SCORE || score < MIN_SCORE) {
            throw new IllegalArgumentException("점수는 0 ~ 10 사이 값이어야 합니다.");
        }
    }

    public boolean isStrike() {
        return score == MAX_SCORE;
    }

    public boolean isSpare(Score secondScore) {
        return (score + secondScore.getValue()) == MAX_SCORE;
    }

    public boolean isGutter() {
        return score == MIN_SCORE;
    }

    public Score sum(Score value) {
        if (value == null) {
            return this;
        }

        return valueOf(score + value.score);
    }

    public int getValue() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("%d", score);
    }
}
