package nl.hu.cisq1.lingo.trainer.application;


import nl.hu.cisq1.lingo.trainer.data.GameRepository;
import nl.hu.cisq1.lingo.trainer.domain.game.Progress;
import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrainerServiceTest {

    @Test
    @DisplayName("Start game")
    void startGame(){
        TrainerService mockService = mock(TrainerService.class);
        when(mockService.startGame())
                .thenReturn(Optional.of(new Word(word)));


    }

}
