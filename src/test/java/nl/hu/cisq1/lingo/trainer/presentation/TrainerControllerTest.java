package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.TrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.DisplayName;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(TrainerController.class)
@AutoConfigureMockMvc
@DisplayName("TrainerController")
class TrainerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainerService trainerService;

    @InjectMocks
    private TrainerController trainerController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Create game")
    void startGame() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/trainer/game");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("Create a new round")
    void startNewRound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/trainer/game/1/round");


        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("Action not allowed")
    void createRoundNotAllowed() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/trainer/game/id/round");


        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Guess attempt")
    void guess() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders
                .post("/trainer/game/1/guess");


        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();;
    }
}
