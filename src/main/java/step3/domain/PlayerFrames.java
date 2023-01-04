package step3.domain;

import step3.domain.frame.Frame;
import step3.domain.frame.Frames;

import java.util.stream.Stream;

public class PlayerFrames {
    private final Player player;
    private final Frame frame;

    private PlayerFrames(Player player, Frame frame) {
        this.player = player;
        this.frame = frame;
    }

    public static PlayerFrames of(Player player, Frame firstFrame) {
        return new PlayerFrames(player, firstFrame);
    }

    public Stream<Frame> getFrameInfo() {
        return Frames.frameInfo(frame);
    }

    public Player getPlayer() {
        return player;
    }
}
