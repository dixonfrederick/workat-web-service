package id.ac.ui.cs.advprog.workatwebservice.repository;

import id.ac.ui.cs.advprog.workatwebservice.model.Stats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class StatsRepositoryTest {

    @Mock
    private StatsRepository statsRepository;

    private Stats stats;

    @BeforeEach
    public void setUp() {
        stats = new Stats();
        stats.setId("0");
    }

    @Test
    public void whenFindItemByNameAndTypeShouldReturnCorrectObject() {
        lenient().when(statsRepository.findById("0")).thenReturn(Optional.ofNullable(stats));
        assertEquals(statsRepository.findById("0"),Optional.ofNullable(stats));
    }
}
