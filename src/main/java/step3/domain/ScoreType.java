package step3.domain;

import java.util.List;

public enum ScoreType {
    SPARED("/"),
    STRIKE("X"),
    GUTTER("-");

    private final String symbol;

    ScoreType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static String convertSymbol(List<Score> scores, int index) {
        if (scores.get(index).isGutter()) {
            return ScoreType.GUTTER.getSymbol();
        }

        if (index == 1 && scores.get(0).isSpare(scores.get(1))) {
            return ScoreType.SPARED.getSymbol();
        }

        if (scores.get(index).isStrike()) {
            return ScoreType.STRIKE.getSymbol();
        }

        return scores.get(index).toString();
    }
}
