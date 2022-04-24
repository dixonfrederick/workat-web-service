package id.ac.ui.cs.advprog.workatwebservice.repository;

import id.ac.ui.cs.advprog.workatwebservice.core.GameObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameRepositoryTest {

    private GameRepository gameRepository;

    private GameObject gameObject;

    @BeforeEach
    public void setUp() {
        gameRepository = new GameRepository();
        gameObject = new GameObject();
        gameRepository.create(gameObject);
    }

    @Test
    public void whenFindItemByNameAndTypeShouldReturnCorrectObject() throws Exception {
        GameObject dummyGameObject = new GameObject();
        dummyGameObject.setGameId("0");
        gameRepository.create(dummyGameObject);

        assertEquals(gameRepository.getById("0"),dummyGameObject);
    }
}
