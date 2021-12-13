package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exception.HintSizeDoesNotMatchException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.LetterFeedback.*;

public class Feedback {
    private String wordToGuess;
    private List<LetterFeedback> letterFeedback;

    public Feedback(String wordToGuess, List<LetterFeedback> letterFeedback) {
        this.wordToGuess = wordToGuess;
        this.letterFeedback = letterFeedback;
    }

    public Feedback(List<nl.hu.cisq1.lingo.trainer.domain.LetterFeedback> nCopies) {
    }

    public List<nl.hu.cisq1.lingo.trainer.domain.LetterFeedback> getLetterFeedback() {
        return letterFeedback;
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
                marked.add(CORRECT);
            } else {
                marked.add(null);
            }
        }

        for (int i = 0; i < lettersToGuess.size(); i++) {
            String attemptLetter = attemptLetters.get(i);

            if (marked.get(i) == CORRECT) {
                continue;
            }

            if (lettersToGuess.contains(attemptLetter)) {
                int index = lettersToGuess.indexOf(attemptLetter);
                lettersToGuess.set(index, " ");
                marked.set(i, PRESENT);
            } else {
                marked.set(i, ABSENT);
            }
        }

        return new Feedback(wordToGuess, marked);
    }

    public List<String> giveHint(List<String> previousHint){
        if(previousHint.size() != letterFeedback.size()) {
            throw new HintSizeDoesNotMatchException();
        }

        String[] letters = wordToGuess.split(" ");
        List<String> hint = new ArrayList<>(previousHint);

        for(int i = 0; i < letterFeedback.size(); i++){
            if(letterFeedback.get(i) == CORRECT){
                hint.set(i, letters[i]);
            } else {
                hint.set(i, ".");
            }
        }

        return hint;
    }

}
