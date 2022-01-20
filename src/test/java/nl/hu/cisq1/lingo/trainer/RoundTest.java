package nl.hu.cisq1.lingo.trainer;

import nl.hu.cisq1.lingo.trainer.application.game.GameService;
import nl.hu.cisq1.lingo.trainer.domain.round.Round;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Round")
@SpringBootTest
public class RoundTest {

    @Autowired
    private GameService gameService;

    @Test
    @DisplayName("give first letter hint")
    void firstHint(){
        String word = "BAARD";
        List<String> firstHints = Round.getFirstLetter(word);

        List<String> expected = List.of("B", ".", ".", ".", ".");

        assertEquals(expected, firstHints);
        }
}
