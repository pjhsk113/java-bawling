package step2.view;

import step2.domain.Player;
import step2.domain.Score;
import step2.domain.ScoreType;
import step2.domain.frame.Frame;
import step2.domain.frame.Frames;
import step2.domain.score.NormalScores;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class OutputView {
    private static final String ROUND_FORMAT = "| NAME |%s|";
    private static final String SCORES_FORMAT = "| %4s |%s|";

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
                frames.stream()
                        .map(frame -> String.format("%-4s", scoreOf(frame)))
                        .collect(joining("|  "))
        );
    }

    private static String scoreOf(Frame frame) {
        if (frame == null) {
            return "";
        }

        return eachScoreOf(
                frame.getScores()
                        .stream()
                        .collect(toList()));
    }
    private static String eachScoreOf(List<Score> scores) {
        return IntStream.range(0, scores.size())
                .mapToObj(index -> scores.get(index) != null
                        ? toScoreType(scores, index)
                        : null)
                .filter(Objects::nonNull)
                .collect(joining("|"));
    }

    private static String toScoreType(List<Score> scores, int index) {
        if (scores.get(index).isGutter()) {
            return ScoreType.GUTTER.getSymbol();
        }
        if (scores.get(index).isStrike()) {
            return ScoreType.STRIKE.getSymbol();
        }
        if (index == 1 && scores.get(0).isSpare(scores.get(1))) {
            return ScoreType.SPARED.getSymbol();
        }
        return scores.get(index).toString();
    }
}
