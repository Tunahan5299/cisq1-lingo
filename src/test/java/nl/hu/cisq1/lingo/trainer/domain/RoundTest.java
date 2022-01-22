package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.round.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.round.Round;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Round")
public class RoundTest {

    @Test
    @DisplayName("Next word is 5 letters")
    void nextWordIsFiveLetters() {
        String wordToGuess = "HARTJES";
        Round round = new Round(wordToGuess);
        int actual = round.getNextWordLength();
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Next word is 6 letters")
    void nextWordIsSixLetters() {
        String wordToGuess = "KLOPT";
        Round round = new Round(wordToGuess);
        int actual = round.getNextWordLength();
        int expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Next word is 7 letters")
    void nextWordIsSevenLetters() {
        String wordToGuess = "LETTER";
        Round round = new Round(wordToGuess);
        int actual = round.getNextWordLength();
        int expected = 7;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Guess attempt")
    void guess() {
        String wordToGuess = "KLOPT";
        String attempt = "KLEUR";
        Round round = new Round(wordToGuess);
        round.guess(attempt);
    }

    @Test
    @DisplayName("Get First hint")
    void getFirstHintTest(){
        String wordToGuess = "hallo";
        Round round = new Round(wordToGuess);
        List<String> actual = round.getCurrentHint();
        List<String> expected = List.of("h", ".", ".", ".", ".");

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get current hint after guess")
    void getCurrentHintTest(){
        String wordToGuess = "hallo";
        String attempt = "halla";
        Round round = new Round(wordToGuess);
        round.guess(attempt);
        List<String> actual = round.getCurrentHint();
        List<String> expected = List.of("h", "a", "l", "l", ".");

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get feedbackhistory")
    void getFeedbackHistory(){
        String wordToGuess = "hallo";
        String attempt = "halla";
        Round round = new Round(wordToGuess);
        round.guess(attempt);
        List<Feedback> actual = round.getFeedbackHistory();
        assertThat(actual).hasOnlyElementsOfType(Feedback.class);
    }

}
