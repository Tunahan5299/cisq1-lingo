package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.trainer.data.GameRepository;
import nl.hu.cisq1.lingo.trainer.domain.exception.GameNotFoundException;
import nl.hu.cisq1.lingo.trainer.domain.game.Game;
import nl.hu.cisq1.lingo.trainer.domain.game.Progress;
import org.springframework.stereotype.Service;

import static nl.hu.cisq1.lingo.trainer.domain.game.GameState.ELIMINATED;
import static nl.hu.cisq1.lingo.trainer.domain.game.GameState.WAITING_FOR_ROUND;

@Service
public class TrainerService {
    private final WordService wordService;
    private final GameRepository gameRepository;

    public TrainerService(WordService wordService, GameRepository gameRepository) {
        this.wordService = wordService;
        this.gameRepository = gameRepository;
    }

    public Progress startGame() {
        String wordToGuess = this.wordService.provideRandomWord(5);

        Game game = new Game();
        game.startRound(wordToGuess);

        this.gameRepository.save(game);

        return game.getProgress();
    }

    public Progress guess(Long gameId, String attempt) {
        Game game = this.gameRepository.findById(gameId)
                .orElseThrow(GameNotFoundException::new);

        if (!game.getProgress().getCurrentHint().contains(".")) {
            game.attemptCounter = 0;
        }

        game.attemptCounter++;

        if (game.attemptCounter > 5) {
            game.status = ELIMINATED;
        } else {
            if (!game.getProgress().getCurrentHint().contains(".")) {
                game.status = WAITING_FOR_ROUND;
                game.score = game.score + 100;
            } else {
                game.guess(attempt);
            }
        }

        this.gameRepository.save(game);

        return game.getProgress();
    }

    public Progress startNewRound(Long gameId) {
        Game game = this.gameRepository.findById(gameId)
                .orElseThrow(GameNotFoundException::new);

        int nextLength = game.provideNextWordLength();
        String wordToGuess = this.wordService.provideRandomWord(nextLength);
        game.startRound(wordToGuess);

        this.gameRepository.save(game);

        return game.getProgress();
    }
}
