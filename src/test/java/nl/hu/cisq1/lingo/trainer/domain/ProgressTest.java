package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.game.Game;
import nl.hu.cisq1.lingo.trainer.domain.game.GameState;
import nl.hu.cisq1.lingo.trainer.domain.game.Progress;
import nl.hu.cisq1.lingo.trainer.domain.round.Round;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Progress")
public class ProgressTest {

    @Test
    @DisplayName("Get progress from game")
    void getProgress() {
        String wordToGuess = "KLOPT";
        String attempt = "KLOPD";
        Round round = new Round(wordToGuess);
        List<Round> rounds = new ArrayList<>();
        rounds.add(round);
        Game game = new Game(0, 0, GameState.IN_PROGRESS, rounds);

        Progress progress = game.getProgress();
        List<String> expected = List.of("K", ".", ".", ".", ".");
        game.guess(attempt);

        assertEquals(0, progress.getScore());
        assertEquals(0, progress.getAttemptCounter());
        assertEquals(GameState.IN_PROGRESS, progress.getStatus());
        assertEquals(round.getFeedbackHistory(), progress.getFeedbackHistory());
        assertEquals(expected, progress.getCurrentHint());
    }
}
