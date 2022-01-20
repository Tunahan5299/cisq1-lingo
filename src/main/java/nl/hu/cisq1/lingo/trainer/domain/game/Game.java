package nl.hu.cisq1.lingo.trainer.domain.game;

import nl.hu.cisq1.lingo.trainer.domain.exception.ActionNotAllowedException;
import nl.hu.cisq1.lingo.trainer.domain.round.Round;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.game.GameState.*;

// @TODO wat als het woord geraden is --> volgende ronde?
// @TODO wat als het woord na 5x (MAX_ATTEMPTS) niet geraden is --> eliminated?
// @TODO mag je altijd raden?
// @TODO hoe houden we de score bij?
// @TODO voldoende tests en CI, static analysis, security analysis
// @TODO let op Engels; CTRL + ALT + L (reformat)

@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    private Integer score = 0;

    @Enumerated(EnumType.STRING)
    private GameState status = WAITING_FOR_ROUND;

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Round> rounds = new ArrayList<>();

    public Game() {
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
        Round currentRound = getCurrentRound();
        return new Progress(
                id,
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

    private Round getCurrentRound() {
        return this.rounds.get(this.rounds.size() - 1);
    }
}
