package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {
    @Mock
    private GameRepository gameRepository;

    private GameServiceImpl gameService = new GameServiceImpl();

    private GameObject gameObject;

    @BeforeEach
    public void setUp() {
        gameObject = new GameObject();
        gameObject.setGameId("0");
    }

    @Test
    public void testCreateCategory() {
        ReflectionTestUtils.setField(gameService, "gameRepository", gameRepository);
        lenient().when(gameService.createGame(gameObject)).thenReturn(gameObject);
    }

    @Test
    public void testViewGame() {
        ReflectionTestUtils.setField(gameService, "gameRepository", gameRepository);
        lenient().when(gameService.viewGame("0")).thenReturn(gameObject);
        GameObject calledGame = gameService.viewGame("0");
        assertEquals(calledGame.getGameId(), gameObject.getGameId());
    }

    @Test
    public void testSubmitAnswer(){
        gameObject.setCorrectWord("TESTS");
        lenient().when(gameRepository.findById("0")).thenReturn(gameObject);
        ReflectionTestUtils.setField(gameService, "gameRepository", gameRepository);
        assertEquals(gameService.submitAnswer("0", "TESTS").getLetterStates(), "BBBBB");

    }

}
