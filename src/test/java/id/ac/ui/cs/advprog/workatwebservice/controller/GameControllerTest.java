package id.ac.ui.cs.advprog.workatwebservice.controller;

import id.ac.ui.cs.advprog.workatwebservice.core.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.core.answer.Result;
import id.ac.ui.cs.advprog.workatwebservice.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = GameController.class)
public class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    private GameObject gameObject;
    private Result result;

    @BeforeEach
    public void setUp() {
        gameObject = new GameObject();
    }

    @Test
    public void testCreateGame() throws Exception {
        when(gameService.createGame(gameObject)).thenReturn(gameObject);

        mockMvc.perform(post("/api/game")
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testViewGame() throws Exception {
        when(gameService.viewGame(String.valueOf(0))).thenReturn(gameObject);

        mockMvc.perform(get("/api/game/0").contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testSubmitAnswer() throws Exception {
        when(gameService.submitAnswer(String.valueOf(0),"answer")).thenReturn(result);

        mockMvc.perform(get("/api/game/0").contentType(MediaType.APPLICATION_JSON));
    }

}
