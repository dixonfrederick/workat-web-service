package id.ac.ui.cs.advprog.workatwebservice.controller;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.core.answer.Result;
import id.ac.ui.cs.advprog.workatwebservice.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.client.WebClient;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = GameController.class)
class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;
    @MockBean
    private WebClient.Builder webClientBuilder;

    private GameObject gameObject;
    private Result result;

    @BeforeEach
    public void setUp() {
        webClientBuilder.build();
        gameObject = new GameObject();
        result = new Result();
    }

    @Test
    void testCreateGame() throws Exception {
        when(gameService.createGame(gameObject)).thenReturn(gameObject);

        mockMvc.perform(post("/api/game/")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(gameObject)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.attemptAmount").value(0));

        mockMvc.perform(get("/api/game/")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(gameObject)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testViewGame() throws Exception {
        when(gameService.viewGame(String.valueOf(0))).thenReturn(gameObject);

        mockMvc.perform(get("/api/game/0")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(gameObject)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testNonExistentViewGame() throws Exception {
        mockMvc.perform(get("/api/game/0")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(gameObject)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSubmitAnswer() throws Exception {
        when(gameService.submitAnswer(String.valueOf(0),"tests")).thenReturn(result);

        mockMvc.perform(post("/api/game/0")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(result)))
                .andExpect(status().isOk());
    }

}
