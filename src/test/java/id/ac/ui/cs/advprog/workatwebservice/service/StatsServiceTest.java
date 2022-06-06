package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.core.helper.InputProcessor;
import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.model.Stats;
import id.ac.ui.cs.advprog.workatwebservice.repository.GameRepository;
import id.ac.ui.cs.advprog.workatwebservice.repository.StatsRepository;
import id.ac.ui.cs.advprog.workatwebservice.repository.UserRepository;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
@EnableAsync
class StatsServiceTest implements AsyncConfigurer {
    @Mock
    private StatsRepository statsRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private InputProcessor inputProcessor;
    @Spy
    @InjectMocks
    private StatsServiceImpl statsService = new StatsServiceImpl();

    private GameObject gameObject;
    private Stats stats;

    @BeforeEach
    public void setUp() {
        stats = new Stats();
        stats.setId("0");
        gameObject = new GameObject();
        statsRepository.save(stats);
    }

    @Test
    void testCreateStats() {
        ReflectionTestUtils.setField(statsService, "statsRepository", statsRepository);
        ReflectionTestUtils.setField(statsService, "userRepository", userRepository);
        Stats createdStats = statsService.createStats("0");
        assertEquals(createdStats.getId(), stats.getId());
    }


    @Test
    void testUpdateStats() {
        gameObject.setFinalState(true);
        ReflectionTestUtils.setField(statsService, "statsRepository", statsRepository);
        ReflectionTestUtils.setField(statsService, "userRepository", userRepository);
        Stats updatedStats = statsService.updateStats("0", gameObject);
        assertEquals(updatedStats.getTotalMenang(), 1);
    }

    @Test
    void testGetStats() {
        ReflectionTestUtils.setField(statsService, "statsRepository", statsRepository);
        ReflectionTestUtils.setField(statsService, "userRepository", userRepository);
        Optional<Stats> retrievedStats = statsService.getStats("0");
        assertEquals(retrievedStats.get().getId(), stats.getId());
    }

}
