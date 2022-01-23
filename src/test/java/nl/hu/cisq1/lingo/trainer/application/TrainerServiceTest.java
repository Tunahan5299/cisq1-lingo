package nl.hu.cisq1.lingo.trainer.application;


import nl.hu.cisq1.lingo.trainer.data.GameRepository;
import nl.hu.cisq1.lingo.trainer.domain.game.Game;
import nl.hu.cisq1.lingo.trainer.domain.game.GameState;
import nl.hu.cisq1.lingo.trainer.domain.game.Progress;
import nl.hu.cisq1.lingo.trainer.domain.round.Round;
import nl.hu.cisq1.lingo.words.application.WordService;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("TrainerService")
class TrainerServiceTest {

    private static TrainerService trainerService;
    private static GameRepository gameRepository;
    private static WordService wordService;

    @BeforeAll
    static void beforeAll() {
        gameRepository = mock(GameRepository.class);
        wordService = mock(WordService.class);
        trainerService = new TrainerService(wordService, gameRepository);
    }

    @Test
    @DisplayName("Start game")
    void startGame() {
        when(wordService.provideRandomWord(5)).thenReturn(String.valueOf(new Word("KLOPT")));
        Progress progress = trainerService.startGame();

        assertThat(progress).isInstanceOf(Progress.class);
    }

    @Test
    @DisplayName("Create a new round")
    void createNewRound() {
        Round round = new Round("KLOPT");
        List<Round> rounds = new ArrayList<>();
        rounds.add(round);
        Game game = new Game(0, 0, GameState.WAITING_FOR_ROUND, rounds);
        gameRepository.save(game);

        when(gameRepository.findById(game.getProgress().getId()))
                .thenReturn(Optional.of(new Game(0, 0, GameState.WAITING_FOR_ROUND, rounds)));
        when(wordService.provideRandomWord(game.provideNextWordLength())).thenReturn(String.valueOf(new Word("KLOPT")));

        Progress progress = trainerService.startNewRound(game.getProgress().getId());
        assertThat(progress).isInstanceOf(Progress.class);
    }

    @Test
    @DisplayName("Guess attempt")
    void guess() {
        Round round = new Round("KLOPT");
        List<Round> rounds = new ArrayList<>();
        rounds.add(round);
        String attempt = "KLOPT";
        Game game = new Game(0, 0, GameState.WAITING_FOR_ROUND, rounds);
        gameRepository.save(game);

        when(gameRepository.findById(game.getProgress().getId()))
                .thenReturn(Optional.of(new Game(0, 0, GameState.WAITING_FOR_ROUND, rounds)));
        when(wordService.provideRandomWord(game.provideNextWordLength()))
                .thenReturn(String.valueOf(new Word("KLOPT")));

        Progress progress = trainerService.guess(game.getProgress().getId(), attempt);
        assertThat(progress).isInstanceOf(Progress.class);
    }

}
