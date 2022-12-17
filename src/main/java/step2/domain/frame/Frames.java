package step2.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Frames {
    private List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames from(List<Frame> frames) {
        return new Frames(frames);
    }

    public static Frames init() {
        return new Frames(new ArrayList<>());
    }

    public Frames addFrames(Frame currentFrame) {
        if (currentFrame != null) {
            frames.add(currentFrame);
        }

        return new Frames(frames);
    }

    public Stream<Frame> stream() {
        return frames.stream();
    }
}
