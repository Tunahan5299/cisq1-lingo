package nl.hu.cisq1.lingo.trainer.domain.game;

import nl.hu.cisq1.lingo.trainer.domain.round.Feedback;

import java.util.List;

public class Progress {
    private final Long id;
    private final int score;
    private final GameState status;
    private final List<Feedback> feedbackHistory;
    private final List<String> currentHint;

    public Progress(Long id, Integer score, GameState status, List<String> currentHint, List<Feedback> feedbackHistory) {
        this.id = id;
        this.score = score;
        this.status = status;
        this.feedbackHistory = feedbackHistory;
        this.currentHint = currentHint;
    }

    public Long getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public GameState getStatus() {
        return status;
    }

    public List<Feedback> getFeedbackHistory() {
        return feedbackHistory;
    }

    public List<String> getCurrentHint() {
        return currentHint;
    }
}
