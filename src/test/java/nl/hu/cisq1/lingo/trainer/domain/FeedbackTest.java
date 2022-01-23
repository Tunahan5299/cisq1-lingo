package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.HintSizeDoesNotMatchException;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
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
                Arguments.of("BAARD", "BONJE", List.of(CORRECT, LetterFeedback.ABSENT, LetterFeedback.ABSENT, LetterFeedback.ABSENT, LetterFeedback.ABSENT)),
                Arguments.of("BAARD", "BARST", List.of(CORRECT, CORRECT, LetterFeedback.PRESENT, LetterFeedback.ABSENT, LetterFeedback.ABSENT)),
                Arguments.of("BAARD", "BEDDE", List.of(CORRECT, LetterFeedback.ABSENT, LetterFeedback.PRESENT, LetterFeedback.ABSENT, LetterFeedback.ABSENT)),
                Arguments.of("BAARD", "BABYS", List.of(CORRECT, CORRECT, LetterFeedback.ABSENT, LetterFeedback.ABSENT, LetterFeedback.ABSENT)),
                Arguments.of("BAARD", "BRAAM", List.of(CORRECT, LetterFeedback.PRESENT, CORRECT, LetterFeedback.PRESENT, LetterFeedback.ABSENT)),
                Arguments.of("BAARD", "AARDE", List.of(LetterFeedback.PRESENT, CORRECT, LetterFeedback.PRESENT, LetterFeedback.PRESENT, LetterFeedback.ABSENT))
        );
    }

    @Test
    @DisplayName("Give hints")
    void giveHint() {
        List<String> previousHint = List.of("B", ".", ".", "R", "D");
        Feedback feedback = Feedback.generate("BAARD", "WAARD");

        List<String> hint = feedback.giveHint(previousHint);

        List<String> expected = List.of("B", "A", "A", "R", "D");
        assertEquals(expected, hint);
    }

    @Test
    @DisplayName("Hint size does not match")
    void hintSizeDoesNotMatch() {
        List<String> previousHint = List.of("B", ".", ".", "R", "D", "D");
        Feedback feedback = Feedback.generate("BAARD", "WAARD");

        Throwable thrown = catchThrowable(() -> {
            feedback.giveHint(previousHint);
        });

        assertThat(thrown).isInstanceOf(HintSizeDoesNotMatchException.class);
    }

    @Test
    @DisplayName("Word is guessed correctly")
    void wordIsCorrect() {
        String wordToGuess = "KLOPT";
        String attempt = "KLOPT";

        Feedback feedback = Feedback.generate(wordToGuess, attempt);
        List<LetterFeedback> actual = feedback.getLetterFeedback();

        List<LetterFeedback> expected = List.of(CORRECT, CORRECT, CORRECT, CORRECT, CORRECT);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Word is too long")
    void wordIsTooLong() {
        String wordToGuess = "KLOPT";
        String attempt = "KLOPPEN";

        Feedback feedback = Feedback.generate(wordToGuess, attempt);
        List<LetterFeedback> actual = feedback.getLetterFeedback();
        assertEquals(feedback.getLetterFeedback(), actual);
    }
}
