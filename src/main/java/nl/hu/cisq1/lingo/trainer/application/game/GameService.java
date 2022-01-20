package nl.hu.cisq1.lingo.trainer.application.game;

import nl.hu.cisq1.lingo.trainer.application.round.RoundService;
import nl.hu.cisq1.lingo.trainer.data.game.GameRepository;
import nl.hu.cisq1.lingo.trainer.data.round.RoundRepository;
import nl.hu.cisq1.lingo.trainer.domain.exception.GameNotInProgressException;
import nl.hu.cisq1.lingo.trainer.domain.game.Game;
import nl.hu.cisq1.lingo.trainer.domain.round.Feedback;
import nl.hu.cisq1.lingo.trainer.domain.round.LetterFeedback;
import nl.hu.cisq1.lingo.trainer.domain.round.Round;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.game.GameState.INPROGRESS;

@Service
@Transactional
public class GameService {
    private final GameRepository gameRepository;
    private final RoundRepository roundRepository;
    private final RoundService roundService;

    public GameService(GameRepository gameRepository, RoundRepository roundRepository, RoundService roundService) {
        this.gameRepository = gameRepository;
        this.roundRepository = roundRepository;
        this.roundService = roundService;
    }

    public List<String> startGame(){
        Round firstRound = roundService.createRound(7);
        String wordToGuess = firstRound.getWordToGuess();

        List<Round> RoundList = new ArrayList<Round>();
        RoundList.add(firstRound);

        Game startGame = new Game(0, INPROGRESS, RoundList);
        gameRepository.save(startGame);
        roundRepository.save(firstRound);
        startGame.setRounds(RoundList);
        gameRepository.save(startGame);

        return firstRound.getFirstLetter(wordToGuess);
    }

    public List<LetterFeedback> giveFeedback(Long id, String attempt){
        List<Round> rounds = findRoundByGameId(id);
        String wordToGuess = rounds.toString();
        String filteredWordToGuess = wordToGuess.replaceAll("[\\[\\](){}]","");;
        System.out.println(filteredWordToGuess);
        Feedback feedback = Feedback.generate(filteredWordToGuess, attempt);

        return feedback.getLetterFeedback();
    }

    @Transactional
    public List<Round> findRoundByGameId(Long id){
        Game game = gameRepository.findById(id).orElseThrow();
        if(game.getStatus() == INPROGRESS) {
            return this.gameRepository.findById(id).orElseThrow(NullPointerException::new).getRounds();
        }else{
            throw new GameNotInProgressException();
        }
    }
}
