package nl.hu.cisq1.lingo.trainer.data.game;

import nl.hu.cisq1.lingo.trainer.domain.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GameRepository extends JpaRepository<Game, String> {
    @Query(nativeQuery=true, value="UPDATE game SET score = ?1 WHERE ")
}
