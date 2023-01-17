package step4.view;

import step4.domain.PlayerFrames;
import step4.domain.Score;
import step4.domain.ScoreType;
import step4.domain.frame.Frame;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class OutputView {
    private static final String ROUND_FORMAT = "| NAME |%s|";
    private static final String SCORES_FORMAT = "| %4s |  %s|";

    private OutputView() {
    }

    public static void printFrame(PlayerFrames playerFrames) {
        String name = playerFrames.getPlayer().getName();
        System.out.println(printFramesTemplate());
        System.out.println(printScores(playerFrames, name, frame -> String.format("%-4s", getScores(frame))));
        System.out.println(printScores(playerFrames, "", frame -> String.format("%-4s", calculationOf(frame))));
    }

    private static String printFramesTemplate() {
        return String.format(
                ROUND_FORMAT,
                IntStream.rangeClosed(1, 10)
                        .mapToObj(frameRound -> String.format("  %02d  ", frameRound))
                        .collect(joining("|"))
        );
    }

    private static String printScores(PlayerFrames playerFrames, String name, Function<Frame, String> mapper) {
        return String.format(
                SCORES_FORMAT,
                name,
                playerFrames.getFrameInfo()
                        .map(mapper)
                        .collect(joining("|  "))
        );
    }

    private static String getScores(Frame frame) {
        if (frame == null) {
            return "";
        }

        return joiningScores(frame.getScores()
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
