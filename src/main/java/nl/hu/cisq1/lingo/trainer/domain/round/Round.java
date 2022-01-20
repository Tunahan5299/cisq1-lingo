package nl.hu.cisq1.lingo.trainer.domain.round;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Round {
    public static final int MAX_ATTEMPTS = 5;
    @Id
    @GeneratedValue
    private Long id;
    private String wordToGuess;

    public String getWordToGuess() {
        return wordToGuess;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private final List<Feedback> feedbackHistory = new ArrayList<>();

    public Round() {}

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    public static int getNextWordLength(int priorWordLength) {
        if (priorWordLength == 5) {
            return 6;
        }
        if (priorWordLength == 6) {
            return 7;
        }
        if (priorWordLength == 7) {
            return 5;
        }
        return 5;
    }

    public static List<String> getFirstLetter(String wordToGuess) {
        List<String> hint = new ArrayList<>();
        String word = String.valueOf(wordToGuess);
        String[] letters = word.split("");

        for(int i = 1; i < letters.length; i++){
            if(i == 1){
                hint.add(letters[0]);
            }
            hint.add(".");
        }
        return hint;
    }

    @Override
    public String toString() {
        return "" + wordToGuess;
    }

}
