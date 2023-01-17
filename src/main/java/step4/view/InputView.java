package step4.view;

import step4.domain.Player;
import step4.domain.Score;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PLAYER_NUMBER = "How many people?";
    private static final String INPUT_PLAYER_MESSAGE = "플레이어 %d의 이름은(3 english letters)?:";
    private static final String INPUT_PITCHING_MESSAGE = "%d프레임 투구";
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() { }

    public static int inputPlayerCount() {
        System.out.println(INPUT_PLAYER_NUMBER);
        return SCANNER.nextInt();
    }

    public static Player inputPlayerName(int index) {
        String.format(INPUT_PLAYER_MESSAGE, index);
        return Player.from(SCANNER.nextLine());
    }

    public static Score inputFrameScore(int frame) {
        System.out.println(String.format(INPUT_PITCHING_MESSAGE, frame));
        return Score.from(SCANNER.nextInt());
    }
}
