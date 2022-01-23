package nl.hu.cisq1.lingo.trainer.domain.game;

import nl.hu.cisq1.lingo.trainer.domain.exception.ActionNotAllowedException;
import nl.hu.cisq1.lingo.trainer.domain.round.Round;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.game.GameState.*;

@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    public Integer score = 0;

    public int attemptCounter;

    @Enumerated(EnumType.STRING)
    public GameState status = WAITING_FOR_ROUND;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Round> rounds = new ArrayList<>();

    public Game() {
    }

    public Game(GameState IN_PROGRESS) {
    }

    public Game(Integer score, int attemptCounter, GameState status, List<Round> rounds) {
        this.score = score;
        this.attemptCounter = attemptCounter;
        this.status = status;
        this.rounds = rounds;
    }

    public void startRound(String wordToGuess) {
        if (this.status != WAITING_FOR_ROUND) {
            throw new ActionNotAllowedException();
        }

        Round round = new Round(wordToGuess.toUpperCase());
        this.rounds.add(round);
        this.status = IN_PROGRESS;
    }

    public Progress getProgress() {
        return new Progress(
                id,
                attemptCounter,
                score,
                status,
                getCurrentRound().getCurrentHint(),
                getCurrentRound().getFeedbackHistory()
        );
    }

    public int provideNextWordLength() {
        return this.getCurrentRound().getNextWordLength();
    }

    public void guess(String attempt) {
        this.getCurrentRound().guess(attempt);
    }

    public Integer getScore() {
        return score;
    }

    public GameState getStatus() {
        return status;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public int getAttemptCounter() {
        return attemptCounter;
    }

    private Round getCurrentRound() {
        return this.rounds.get(this.rounds.size() - 1);
    }
}
