package nl.hu.cisq1.lingo.trainer.presentation.game;

import nl.hu.cisq1.lingo.trainer.application.game.GameService;
import nl.hu.cisq1.lingo.trainer.application.round.RoundService;
import nl.hu.cisq1.lingo.trainer.application.words.WordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
    private final WordService wordService;
    private final RoundService roundService;

    public GameController(GameService gameService, WordService wordService, RoundService roundService) {
        this.gameService = gameService;
        this.wordService = wordService;
        this.roundService = roundService;
    }
}
