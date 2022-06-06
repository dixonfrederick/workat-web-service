package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.core.helper.InputProcessor;
import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
@EnableAsync
class GameServiceTest implements AsyncConfigurer {
    @Mock
    private GameRepository gameRepository;
    @Mock
    private InputProcessor inputProcessor;
    @Spy
    @InjectMocks
    private GameServiceImpl gameService = new GameServiceImpl();

    private GameObject gameObject;

    @BeforeEach
    public void setUp() {
        gameObject = new GameObject();
        gameObject.setGameId("0");
    }

    @Test
    void testCreateGame() {
        ReflectionTestUtils.setField(gameService, "gameRepository", gameRepository);
        GameObject createdGame = gameService.createGame(gameObject);
        assertEquals(createdGame.getGameId(), gameObject.getGameId());
    }

    @Test
    void testViewGame() {
        ReflectionTestUtils.setField(gameService, "gameRepository", gameRepository);
        lenient().when(gameService.viewGame("0")).thenReturn(gameObject);
        GameObject calledGame = gameService.viewGame("0");
        assertEquals(calledGame.getGameId(), gameObject.getGameId());
    }

    @Test
    void testSubmitAnswer(){
        gameObject.setCorrectWord("TESTS");
        gameRepository.save(gameObject);
        lenient().when(gameRepository.findByGameId("0")).thenReturn(gameObject);
        ReflectionTestUtils.setField(gameService, "gameRepository", gameRepository);
        ReflectionTestUtils.setField(gameService, "inputProcessor", inputProcessor);
        String letterStates = gameService.submitAnswer("0", "TESTS").getLetterStates();
        assertEquals("BBBBB", letterStates);
    }

}
