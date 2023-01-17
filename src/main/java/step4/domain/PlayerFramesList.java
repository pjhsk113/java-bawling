package step4.domain;

import step4.domain.frame.NormalFrame;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class PlayerFramesList {
    private final List<PlayerFrames> playerFramesList;

    private PlayerFramesList(List<PlayerFrames> playerFramesList) {
        this.playerFramesList = playerFramesList;
    }

    public static PlayerFramesList of(List<PlayerFrames> playersFrames) {
        return new PlayerFramesList(playersFrames);
    }

    public static PlayerFramesList init(Players players) {
        return players.stream()
                .map(player -> PlayerFrames.of(player, NormalFrame.init()))
                .collect(collectingAndThen(toList(), PlayerFramesList::of));
    }

    public Stream<PlayerFrames> stream() {
        return playerFramesList.stream();
    }

}
