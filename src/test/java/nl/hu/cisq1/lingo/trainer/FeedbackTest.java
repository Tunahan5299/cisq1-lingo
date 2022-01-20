package nl.hu.cisq1.lingo.trainer;

import nl.hu.cisq1.lingo.trainer.domain.round.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.round.LetterFeedback;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static nl.hu.cisq1.lingo.trainer.domain.round.LetterFeedback.CORRECT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Feedback")
public class FeedbackTest {

    @ParameterizedTest
    @MethodSource("examples")
    @DisplayName("for correct attempt")
    void wordIsGuessed(String wordToGuess, String attempt, List<LetterFeedback> expected) {
        Feedback feedback = Feedback.generate(wordToGuess, attempt);
        List<LetterFeedback> actual = feedback.getLetterFeedback();
        assertEquals(expected, actual);
    }

    public static Stream<Arguments> examples() {
        return Stream.of(
                Arguments.of("BAARD", "BAARD", List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT)),
                Arguments.of("BAARD", "BERGEN", List.of(LetterFeedback.INVALID, LetterFeedback.INVALID, LetterFeedback.INVALID, LetterFeedback.INVALID, LetterFeedback.INVALID)),
                Arguments.of("BAARD", "BONJE", List.of(CORRECT, LetterFeedback.ABSENT, LetterFeedback.ABSENT, LetterFeedback.ABSENT, LetterFeedback.ABSENT)),
                Arguments.of("BAARD", "BARST", List.of(CORRECT, CORRECT, LetterFeedback.PRESENT, LetterFeedback.ABSENT, LetterFeedback.ABSENT)),
                Arguments.of("BAARD", "BEDDE", List.of(CORRECT, LetterFeedback.ABSENT, LetterFeedback.PRESENT, LetterFeedback.ABSENT, LetterFeedback.ABSENT)),
                Arguments.of("BAARD", "BABYS", List.of(CORRECT, CORRECT, LetterFeedback.ABSENT, LetterFeedback.ABSENT, LetterFeedback.ABSENT)),
                Arguments.of("BAARD", "BRAAM", List.of(CORRECT, LetterFeedback.PRESENT, CORRECT, LetterFeedback.PRESENT, LetterFeedback.ABSENT)),
                Arguments.of("BAARD", "AARDE", List.of(LetterFeedback.PRESENT, CORRECT, LetterFeedback.PRESENT, LetterFeedback.PRESENT, LetterFeedback.ABSENT))
        );
    }

    @DisplayName("Give hints")
    void giveHint() {
        List<String> previousHint = List.of("B", ".", ".", "R", "D");
        Feedback feedback = Feedback.generate("BAARD", "WAARD");

        List<String> hint = feedback.giveHint(previousHint);

        List<String> expected = List.of();
        assertEquals(expected, hint);
    }

    @Test
    @DisplayName("Woord is goed geraden")
    void wordIsCorrect(){
        String wordToGuess = "BAARD";
        String attempt = "BAARD";

        Feedback feedback = Feedback.generate(wordToGuess, attempt);
        List<LetterFeedback> actual = feedback.getLetterFeedback();

        List<LetterFeedback> expected = List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Woord is te lang")
    void wordIsTooLong(){
        String wordToGuess = "BAARD";
        String attempt = "BAARDSDD";

        Feedback feedback = Feedback.generate(wordToGuess, attempt);
        List<LetterFeedback> actual = feedback.getLetterFeedback();
        assertEquals(feedback.getLetterFeedback(), actual);


    }
}
