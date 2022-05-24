package id.ac.ui.cs.advprog.workatwebservice.repository;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class GameRepositoryTest {

    @Mock
    private GameRepository gameRepository;

    private GameObject gameObject;

    @BeforeEach
    public void setUp() {
        gameObject = new GameObject();
        gameObject.setGameId("0");
    }

    @Test
    public void whenFindItemByNameAndTypeShouldReturnCorrectObject() {
        lenient().when(gameRepository.findByGameId("0")).thenReturn(gameObject);
        assertEquals(gameRepository.findByGameId("0"),gameObject);
    }
}
