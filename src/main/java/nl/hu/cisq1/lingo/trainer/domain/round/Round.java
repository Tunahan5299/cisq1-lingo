package nl.hu.cisq1.lingo.trainer.domain.round;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Round {
    @Id
    @GeneratedValue
    private Long id;

    private String wordToGuess;

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private final List<Feedback> feedbackHistory = new ArrayList<>();

    public Round() {
    }

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    public int getNextWordLength() {
        int currentWordLength = this.wordToGuess.length();

        if (currentWordLength == 5) {
            return 6;
        }

        if (currentWordLength == 6) {
            return 7;
        }
        return 5;
    }

    private List<String> getFirstLetter() {
        List<String> hint = new ArrayList<>();
        String word = String.valueOf(wordToGuess);
        String[] letters = word.split("");

        for (int i = 1; i < letters.length; i++) {
            if (i == 1) {
                hint.add(letters[0]);
            }
            hint.add(".");
        }
        return hint;
    }

    public List<String> getCurrentHint() {
        List<String> hint = this.getFirstLetter();
        if (this.feedbackHistory.size() == 0) {
            return hint;
        }
        for (Feedback feedback : this.feedbackHistory) {
            hint = feedback.giveHint(hint);
        }
        return hint;
    }

    public void guess(String attempt) {
        Feedback feedback = Feedback.generate(wordToGuess, attempt);
        this.feedbackHistory.add(feedback);
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public List<Feedback> getFeedbackHistory() {
        return feedbackHistory;
    }
}
