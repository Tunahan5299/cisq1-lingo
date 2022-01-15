package nl.hu.cisq1.lingo.trainer.domain.game;

import nl.hu.cisq1.lingo.trainer.domain.round.Feedback;

import java.util.List;

public class Game {
    private int Id;
    private List<Feedback> Rounds;
    private Score Result;
    private nl.hu.cisq1.lingo.trainer.domain.game.GameState GameState;
    private nl.hu.cisq1.lingo.trainer.domain.game.Player Player;

    public Game(int id, List<Feedback> rounds, Score result, nl.hu.cisq1.lingo.trainer.domain.game.GameState gameState, nl.hu.cisq1.lingo.trainer.domain.game.Player player) {
        Id = id;
        Rounds = rounds;
        Result = result;
        GameState = gameState;
        Player = player;
    }

    public int getId() {
        return Id;
    }

    public List<Feedback> getRounds() {
        return Rounds;
    }

    public Score getResult() {
        return Result;
    }

    public nl.hu.cisq1.lingo.trainer.domain.game.GameState getGameState() {
        return GameState;
    }

    public nl.hu.cisq1.lingo.trainer.domain.game.Player getPlayer() {
        return Player;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setRounds(List<Feedback> rounds) {
        Rounds = rounds;
    }

    public void setResult(Score result) {
        Result = result;
    }

    public void setGameState(nl.hu.cisq1.lingo.trainer.domain.game.GameState gameState) {
        GameState = gameState;
    }

    public void setPlayer(nl.hu.cisq1.lingo.trainer.domain.game.Player player) {
        Player = player;
    }
}
