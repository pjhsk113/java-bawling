package step3.view;

import step3.domain.Player;
import step3.domain.Score;
import step3.domain.ScoreType;
import step3.domain.frame.Frame;
import step3.domain.frame.Frames;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class OutputView {
    private static final String ROUND_FORMAT = "| NAME |%s|";
    private static final String SCORES_FORMAT = "| %4s |  %s|";

    private OutputView() {
    }

    public static void printFrame(Player player, Frames frames) {
        System.out.println(printFramesTemplate());
        System.out.println(printScores(player, frames, frame -> String.format("%-4s", getScores(frame))));
        System.out.println(printScores(player, frames, frame -> String.format("%-4s", calculationOf(frame))));
    }

    private static String printFramesTemplate() {
        return String.format(
                ROUND_FORMAT,
                IntStream.rangeClosed(1, 10)
                        .mapToObj(frameRound -> String.format("  %02d  ", frameRound))
                        .collect(joining("|"))
        );
    }

    private static String printScores(Player player, Frames frames, Function<Frame, String> mapper) {
        return String.format(
                SCORES_FORMAT,
                player.getName(),
                frames.frameInfo()
                        .map(mapper)
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
                        ? ScoreType.convertSymbol(scores, index)
                        : null)
                .filter(Objects::nonNull)
                .collect(joining("|"));
    }

    private static String calculationOf(Frame frame) {
        if (frame == null) {
            return "";
        }
        int calculatedScore = frame.calculateScore();
        return calculatedScore == -1 ? "" : String.valueOf(calculatedScore);
    }

}
