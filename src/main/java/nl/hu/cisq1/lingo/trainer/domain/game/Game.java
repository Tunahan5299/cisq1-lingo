package nl.hu.cisq1.lingo.trainer.domain.game;

import nl.hu.cisq1.lingo.trainer.domain.round.Round;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static nl.hu.cisq1.lingo.trainer.domain.game.GameState.INPROGRESS;

@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    private Integer score = 0;

    public GameState getStatus() {
        return status;
    }

    @Enumerated(EnumType.STRING)
    private GameState status = INPROGRESS;

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Round> rounds = new ArrayList<>();

    public Game() {}
    public Game(Integer score, GameState status) {
        this.score = score;
        this.status = status;
    }

    public Game(int i, GameState inprogress, List<Round> roundList) {
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
}
