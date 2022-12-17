package step2.domain;

public class Player {
    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player from(String name) {
        validate(name);
        return new Player(name);
    }

    private static void validate(String name) {
        if (name.length() > 3) {
            throw new IllegalArgumentException("플레이어 이름은 최대 3글자까지 입력 가능합니다.");
        }

        if (name == null || "".equals(name.trim())) {
            throw new IllegalArgumentException("플레이어 이름을 입력해주세요.");
        }
    }

    public String getName() {
        return name;
    }
}
