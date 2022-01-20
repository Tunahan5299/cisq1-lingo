package nl.hu.cisq1.lingo.trainer.application.round;

import nl.hu.cisq1.lingo.trainer.application.words.WordService;
import nl.hu.cisq1.lingo.trainer.data.round.RoundRepository;
import nl.hu.cisq1.lingo.trainer.domain.round.Round;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoundService {
    private final RoundRepository roundRepository;
    private final WordService wordService;

    public RoundService(RoundRepository roundRepository, WordService wordService) {
        this.roundRepository = roundRepository;
        this.wordService = wordService;
    }

    public Round createRound(int priorWordLength){
        int nextWordLength = Round.getNextWordLength(priorWordLength);
        String wordToGuess = wordService.provideRandomWord(nextWordLength);

        Round NewRound = new Round(wordToGuess.toUpperCase());

        return NewRound;
    }

}
