package id.ac.ui.cs.advprog.workatwebservice.controller;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.model.Stats;
import id.ac.ui.cs.advprog.workatwebservice.service.GameService;
import id.ac.ui.cs.advprog.workatwebservice.service.StatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StatsController.class)
class StatsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatsService statsService;
    @MockBean
    private GameService gameService;
    @MockBean
    private WebClient.Builder webClientBuilder;

    private GameObject gameObject;
    private Stats stats;

    @BeforeEach
    public void setUp() {
        webClientBuilder.build();
        gameObject = new GameObject();
        gameObject.setGameId("0");
        stats = new Stats();
        stats.setId("0");
    }

    @Test
    void testGetListStats() throws Exception {
        Iterable<Stats> statsList = List.of(stats);
        when(statsService.getListStats()).thenReturn(statsList);

        mockMvc.perform(get("/api/stats/")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(statsList)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("0"));
    }

    @Test
    void testCreateStats() throws Exception {
        when(statsService.createStats("0")).thenReturn(stats);

        mockMvc.perform(post("/api/stats/")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(stats)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateStats() throws Exception {
        statsService.createStats("0");
        when(statsService.updateStats("0", gameObject)).thenReturn(stats);

        mockMvc.perform(put("/api/stats/0")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(stats)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetStats() throws Exception {
        when(statsService.getStats("0")).thenReturn(Optional.ofNullable(stats));

        mockMvc.perform(get("/api/stats/0")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(stats)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetNonExistentStats() throws Exception {
        when(statsService.getStats("1")).thenReturn(Optional.ofNullable(stats));

        mockMvc.perform(get("/api/stats/1")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(stats)))
                .andExpect(status().isBadRequest());
    }

}
