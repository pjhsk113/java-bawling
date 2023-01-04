package step3.domain;

public class Score {
    private static final int MAX_SCORE = 10;
    private static final int MIN_SCORE = 0;

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score from(int score) {
        validate(score);
        return new Score(score);
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
        return sum(secondScore) == MAX_SCORE;
    }

    public boolean isGutter() {
        return score == MIN_SCORE;
    }

    public int sum(Score value) {
        return score + value.score;
    }

    public int getValue() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("%d", score);
    }
}
