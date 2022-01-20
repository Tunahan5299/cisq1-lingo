package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.trainer.data.GameRepository;
import nl.hu.cisq1.lingo.trainer.domain.exception.GameNotFoundException;
import nl.hu.cisq1.lingo.trainer.domain.game.Game;
import nl.hu.cisq1.lingo.trainer.domain.game.Progress;
import org.springframework.stereotype.Service;

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

        game.guess(attempt);

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
