package nl.hu.cisq1.lingo.trainer.application.game;

import nl.hu.cisq1.lingo.trainer.data.game.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
}
