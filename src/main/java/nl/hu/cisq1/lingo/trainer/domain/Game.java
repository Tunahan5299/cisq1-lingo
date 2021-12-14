package nl.hu.cisq1.lingo.trainer.domain;

import java.util.List;

public class Game {
    private int Id;
    private List<Feedback> Rounds;
    private Score Result;
    private GameState GameState;
    private Player Player;

    public Game(int id, List<Feedback> rounds, Score result, nl.hu.cisq1.lingo.trainer.domain.GameState gameState, nl.hu.cisq1.lingo.trainer.domain.Player player) {
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

    public nl.hu.cisq1.lingo.trainer.domain.GameState getGameState() {
        return GameState;
    }

    public nl.hu.cisq1.lingo.trainer.domain.Player getPlayer() {
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

    public void setGameState(nl.hu.cisq1.lingo.trainer.domain.GameState gameState) {
        GameState = gameState;
    }

    public void setPlayer(nl.hu.cisq1.lingo.trainer.domain.Player player) {
        Player = player;
    }
}
