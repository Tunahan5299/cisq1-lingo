package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.ActionNotAllowedException;
import nl.hu.cisq1.lingo.trainer.domain.game.Game;
import nl.hu.cisq1.lingo.trainer.domain.game.GameState;
import nl.hu.cisq1.lingo.trainer.domain.game.Progress;
import nl.hu.cisq1.lingo.trainer.domain.round.Round;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static nl.hu.cisq1.lingo.trainer.domain.round.LetterFeedback.CORRECT;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Game")
public class GameTest {

    @Test
    @DisplayName("Start a new round")
    void startRound() {
        String wordToGuess = "KLOPT";
        Game game = new Game();
        game.startRound(wordToGuess);
        game.getProgress();
        GameState actual = game.status;
        GameState expected = GameState.IN_PROGRESS;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Start a new round while its not allowed")
    void startRoundNotAllowed() {
        Game game = new Game(GameState.IN_PROGRESS);
        String wordToGuess = "KLOPT";
        game.startRound(wordToGuess);

        Throwable thrown = catchThrowable(() -> {
            game.startRound(wordToGuess);
        });

        assertThat(thrown).isInstanceOf(ActionNotAllowedException.class);
    }

    @Test
    @DisplayName("Guess word")
    void guess() {
        String wordToGuess = "KLOPT";
        String attempt = "KLOPT";
        Round round = new Round(wordToGuess);
        List<Round> rounds = new ArrayList<>();
        rounds.add(round);
        Game game = new Game(0, 0, GameState.IN_PROGRESS, rounds);
        game.guess(attempt);
    }


}
