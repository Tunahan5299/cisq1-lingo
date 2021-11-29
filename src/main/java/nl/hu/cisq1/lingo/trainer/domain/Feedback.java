package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.LetterFeedback.*;

public class Feedback {
    private List<LetterFeedback> LetterFeedback;

    public Feedback() {
    }

    public Feedback(List<nl.hu.cisq1.lingo.trainer.domain.LetterFeedback> letterFeedback) {
        LetterFeedback = letterFeedback;
    }

    public List<nl.hu.cisq1.lingo.trainer.domain.LetterFeedback> getLetterFeedback() {
        return LetterFeedback;
    }

    public static Feedback generate(String wordToGuess, String attempt){
        if (wordToGuess.length() != attempt.length()){
            return new Feedback(Collections.nCopies(wordToGuess.length(), INVALID));
        }

        List<String> lettersToGuess = Array.
        char[] attemptLetters = attempt.toCharArray();
        List<LetterFeedback> marked = new ArrayList<>();

        for(int i = 0; i < lettersToGuess.length; i++){
            char attemptLetter = attemptLetters[i];
            if (lettersToGuess.get(i) == attemptLetter){
                marked.add(CORRECT);
            } else if (wordToGuess.contains(String.valueOf(attemptLetter))) {
                lettersToGuess[i] = ' ';
                marked.add(PRESENT);
            } else {
                marked.add(ABSENT);
            }
        }

        return new Feedback((marked));
    }

}
