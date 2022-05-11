package id.ac.ui.cs.advprog.workatwebservice.repository;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameRepositoryTest {

    @Mock
    private GameRepository gameRepository;

    private GameObject gameObject;

    @BeforeEach
    public void setUp() {
        gameObject = new GameObject();
        gameRepository.save(gameObject);
    }

    @Test
    public void whenFindItemByNameAndTypeShouldReturnCorrectObject() { // TODO: Need to be changed, null
        GameObject dummyGameObject = new GameObject();
        dummyGameObject.setGameId("0");
        gameRepository.save(dummyGameObject);

        assertEquals(gameRepository.findById("0"),dummyGameObject);
    }
}
