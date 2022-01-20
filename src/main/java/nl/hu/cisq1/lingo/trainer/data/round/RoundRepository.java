package nl.hu.cisq1.lingo.trainer.data.round;

import nl.hu.cisq1.lingo.trainer.domain.round.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> { }
