package step4.domain;

public class PlayerCount {
    private final int count;

    private PlayerCount(int count) {
        this.count = count;
    }

    public static PlayerCount from(int count) {
        validate(count);
        return new PlayerCount(count);
    }

    private static void validate(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("참여자 수는 0보다 커야합니다.");
        }
    }

}
