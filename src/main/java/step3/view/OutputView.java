package step3.view;

import step3.domain.Player;
import step3.domain.Score;
import step3.domain.ScoreType;
import step3.domain.frame.Frame;
import step3.domain.frame.Frames;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class OutputView {
    private static final String ROUND_FORMAT = "| NAME |%s|";
    private static final String SCORES_FORMAT = "| %4s |  %s|";

    private OutputView() {
    }

    public static void printFrame(Player player, Frames frames) {
        System.out.println(printFramesTemplate());
        System.out.println(printScores(player, frames));
    }

    private static String printFramesTemplate() {
        return String.format(
                ROUND_FORMAT,
                IntStream.rangeClosed(1, 10)
                        .mapToObj(frameRound -> String.format("  %02d  ", frameRound))
                        .collect(joining("|"))
        );
    }

    private static String printScores(Player player, Frames frames) {
        return String.format(
                SCORES_FORMAT,
                player.getName(),
                frames.frameInfo()
                        .map(frame -> String.format("%-4s", getScores(frame)))
                        .collect(joining("|  "))
        );
    }

    private static String getScores(Frame frame) {
        if (frame == null) {
            return "";
        }

        return joiningScores(
                frame.getScores()
                        .stream()
                        .collect(toList()));
    }

    private static String joiningScores(List<Score> scores) {
        return IntStream.range(0, scores.size())
                .mapToObj(index -> scores.get(index) != null
                        ? convertSymbol(scores, index)
                        : null)
                .filter(Objects::nonNull)
                .collect(joining("|"));
    }

    private static String convertSymbol(List<Score> scores, int index) {
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
