package nl.hu.cisq1.lingo.trainer;

import nl.hu.cisq1.lingo.trainer.application.game.GameService;
import nl.hu.cisq1.lingo.trainer.domain.round.LetterFeedback;
import nl.hu.cisq1.lingo.trainer.domain.round.Round;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.round.LetterFeedback.ABSENT;
import static nl.hu.cisq1.lingo.trainer.domain.round.LetterFeedback.CORRECT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GameServiceTest {
    @Autowired
    private GameService gameService;

    @Test
    void findRoundByGameId(){
        Long id = Long.valueOf(4);
        List<Round> round;
        round = gameService.findRoundByGameId(id);
        List<Round> rounds = new ArrayList<>();

        assertEquals(rounds, round);

    }

    @Test
    void guess(){
        Long id = 4L;
        String attempt = "sjerq";
        List<LetterFeedback> feedback = gameService.giveFeedback(id, attempt);

        List<LetterFeedback> expected = List.of(CORRECT, CORRECT, CORRECT, CORRECT, ABSENT);

        assertEquals(expected, feedback);


    }

    @Test
    void createRoundTest(){
        List<String> newGame = gameService.startGame();

        List<String> expected = List.of("B", ".", ".", ".", ".");

        assertEquals(expected, newGame);


    }
}
