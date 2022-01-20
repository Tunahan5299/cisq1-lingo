package nl.hu.cisq1.lingo.trainer.domain.round;

import nl.hu.cisq1.lingo.trainer.domain.exception.HintSizeDoesNotMatchException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.round.LetterFeedback.INVALID;

@Entity
public class Feedback {
    @Id
    @GeneratedValue
    private Long id;

    private String attempt;
    private String wordToGuess;

    @ElementCollection
    private List<LetterFeedback> letterFeedback;

    public Feedback() {
    }

    public Feedback(String attempt, String wordToGuess, List<LetterFeedback> letterFeedback) {
        this.attempt = attempt;
        this.wordToGuess = wordToGuess;
        this.letterFeedback = letterFeedback;
    }

    public Feedback(String wordToGuess, List<LetterFeedback> letterFeedback) {
        this.wordToGuess = wordToGuess;
        this.letterFeedback = letterFeedback;
    }


    public Feedback(List<LetterFeedback> nCopies) {
    }

    public static Feedback generate(String wordToGuess, String attempt) {
        if (wordToGuess.length() != attempt.length()) {
            return new Feedback(Collections.nCopies(wordToGuess.length(), INVALID));
        }

        List<String> lettersToGuess = Arrays.asList(wordToGuess.split(""));
        List<String> attemptLetters = Arrays.asList(attempt.split(""));
        List<LetterFeedback> marked = new ArrayList<>();

        for (int i = 0; i < lettersToGuess.size(); i++) {
            String attemptLetter = attemptLetters.get(i);

            if (lettersToGuess.get(i).equals(attemptLetter)) {
                lettersToGuess.set(i, " ");
                marked.add(LetterFeedback.CORRECT);
            } else {
                marked.add(null);
            }
        }

        for (int i = 0; i < lettersToGuess.size(); i++) {
            String attemptLetter = attemptLetters.get(i);

            if (marked.get(i) == LetterFeedback.CORRECT) {
                continue;
            }

            if (lettersToGuess.contains(attemptLetter)) {
                int index = lettersToGuess.indexOf(attemptLetter);
                lettersToGuess.set(index, " ");
                marked.set(i, LetterFeedback.PRESENT);
            } else {
                marked.set(i, LetterFeedback.ABSENT);
            }
        }

        return new Feedback(wordToGuess, marked);
    }

    public List<String> giveHint(List<String> previousHint) {
        if (previousHint.size() != letterFeedback.size()) {
            throw new HintSizeDoesNotMatchException();
        }

        String[] letters = wordToGuess.split(" ");
        List<String> hint = new ArrayList<>(previousHint);

        for (int i = 0; i < letterFeedback.size(); i++) {
            if (letterFeedback.get(i) == LetterFeedback.CORRECT) {
                hint.set(i, letters[i]);
            } else {
                hint.set(i, ".");
            }
        }

        return hint;
    }

    public List<LetterFeedback> getLetterFeedback() {
        return letterFeedback;
    }
}
