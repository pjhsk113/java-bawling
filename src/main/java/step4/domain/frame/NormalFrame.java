package step4.domain.frame;

import step4.domain.score.FinalScores;
import step4.domain.score.NormalScores;
import step4.domain.score.Scores;

import java.util.Objects;
import java.util.stream.Stream;

public class NormalFrame extends Frame {
    private Frame nextFrame;

    private NormalFrame(int frame, Scores scores, Frame nextFrame) {
        super(frame, scores);
        this.nextFrame = nextFrame;
    }

    public static NormalFrame of(int frame, Scores scores, Frame nextFrame) {
        return new NormalFrame(frame, scores, nextFrame);
    }

    public static NormalFrame init() {
        return NormalFrame.of(1, NormalScores.init(), null);
    }

    @Override
    public void next(Scores scores) {
        this.scores = scores;
        if (isFrameNotOver(scores)) {
            return ;
        }

        int nextFrameIndex = frame + 1;

        this.nextFrame = isNormalFrame(nextFrameIndex)
                ? of(nextFrameIndex, NormalScores.init(), null)
                : FinalFrame.of(nextFrameIndex, FinalScores.init());
    }

    private boolean isFrameNotOver(Scores scores) {
        return !scores.isStrike() && !scores.isFrameOver();
    }

    private boolean isNormalFrame(int frame) {
        return frame < 10;
    }

    @Override
    protected int calculateStrike() {
        if (nextFrame == null || !nextFrame.scores.isFrameOver()) {
            return -1;
        }
        if (nextFrame.scores.isStrike()) {
            return nextFrame.calculateTwoStrike(scores.totalScore());
        }
        return scores.totalScore() + nextFrame.scores.totalScore();
    }

    @Override
    protected int calculateTwoStrike(int totalScore) {
        if (nextFrame.scores.isEmpty()) {
            return -1;
        }
        return Stream.concat(scores.stream(), nextFrame.scores.stream())
                .filter(Objects::nonNull)
                .limit(2)
                .reduce(totalScore, (total, score) -> total + score.getValue(), Integer::sum);
    }

    @Override
    protected int calculateSpared() {
        if (nextFrame == null || nextFrame.scores.isEmpty()) {
            return -1;
        }
        return nextFrame.scores
                .stream()
                .limit(1)
                .reduce(scores.totalScore(), (total, score) -> total + score.getValue(), Integer::sum);
    }

    @Override
    public Frame getNextFrame() {
        return nextFrame;
    }
}
