package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.core.Stats;
import id.ac.ui.cs.advprog.workatwebservice.model.User;
import id.ac.ui.cs.advprog.workatwebservice.repository.StatsRepository;
import id.ac.ui.cs.advprog.workatwebservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatsServiceImpl implements StatsService{

    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<Stats> getListStats() {
        return statsRepository.findAll();
    }

    @Override
    public Stats createStats(String id) {
        var newStats = new Stats(id, 0, 0, 0);
        newStats.setUser(userRepository.findByUserId(id));
        statsRepository.save(newStats);
        return newStats;
    }

    @Override
    public Stats updateStats(String id, GameObject gameObject) {
        var updatedStats = statsRepository.getById(id);
        int avg = updatedStats.getAverageAttempt();
        updatedStats.setAverageAttempt((avg * (updatedStats.getTotalKalah() + updatedStats.getTotalMenang())
                + gameObject.getAttemptAmount()) /
                (updatedStats.getTotalKalah() + updatedStats.getTotalMenang() + 1));
        return updatedStats;
    }

    @Override
    public Optional<Stats> getStats(String id) {
        return statsRepository.findById(id);
    }
}
