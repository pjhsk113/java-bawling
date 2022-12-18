package step2.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
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
        List<Frame> frames = new ArrayList<>();
        Frame frame = currentFrame;

        while(frame != null) {
            frames.add(frame);
            frame = frame.getPrevFrame();
        }
        Collections.reverse(frames);
        return from(frames);
    }

    public Stream<Frame> frameInfo() {
        return IntStream.range(0, 10)
                .mapToObj(index -> index < frames.size() ? frames.get(index) : null);
    }
}
