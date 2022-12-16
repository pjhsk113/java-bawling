package step2.view;

import step2.domain.Player;
import step2.domain.Score;

import java.util.Scanner;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class OutputView {
    private static final String ROUND_FORMAT = "| NAME |%s|";

    private OutputView() {
    }

    public static void printFrame(Player player) {
        System.out.println(printFramesTemplate());
    }

    private static String printFramesTemplate() {
        return String.format(
                ROUND_FORMAT,
                IntStream.rangeClosed(1, 10)
                        .mapToObj(frameRound -> String.format("  %02d  ", frameRound))
                        .collect(joining("|"))
        );
    }
}
