package nl.hu.cisq1.lingo.trainer.application.round;

import nl.hu.cisq1.lingo.trainer.data.round.RoundRepository;
import nl.hu.cisq1.lingo.trainer.data.words.SpringWordRepository;
import org.springframework.stereotype.Service;

@Service
public class RoundService {
    private final RoundRepository roundRepository;
    private final SpringWordRepository wordRepository;

    public RoundService(RoundRepository roundRepository, SpringWordRepository wordRepository) {
        this.roundRepository = roundRepository;
        this.wordRepository = wordRepository;
    }
}
