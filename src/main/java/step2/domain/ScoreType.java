package step2.domain;

public enum ScoreType {
    SPARED("/"),
    STRIKE("X"),
    GUTTER("-");

    private final String symbol;

    ScoreType(String symbol) {
        this.symbol = symbol;
    }
}
