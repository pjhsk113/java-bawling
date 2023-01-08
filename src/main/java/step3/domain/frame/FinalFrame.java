package step3.domain.frame;


import step3.domain.Score;
import step3.domain.score.FinalScores;
import step3.domain.score.Scores;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FinalFrame extends Frame {

    private FinalFrame(int frame, Scores scores) {
        super(frame, scores);
    }

    public static FinalFrame of(int frame, Scores scores) {
        return new FinalFrame(frame, scores);
    }

    @Override
    public void next(Scores scores) {
        this.scores = scores;
    }

    @Override
    public Frame getNextFrame() {
        return null;
    }

    @Override
    protected int calculateStrike() {
        FinalScores finalScores = (FinalScores) scores;
        if (!finalScores.filledBonus()) {
            return -1;
        }

        return scores.stream()
                .reduce(0, (total, score) -> total + score.getValue(), Integer::sum);
    }

    @Override
    protected int calculateTwoStrike(int totalScore) {
        List<Score> scoreList = scores.stream().collect(toList());
        Score firstScore = scoreList.get(0);
        Score secondScore = scoreList.get(1);
        if (firstScore == null || secondScore == null) {
            return -1;
        }
        return totalScore + firstScore.getValue() + secondScore.getValue();
    }

    @Override
    protected int calculateSpared() {
        return calculateStrike();
    }
}
