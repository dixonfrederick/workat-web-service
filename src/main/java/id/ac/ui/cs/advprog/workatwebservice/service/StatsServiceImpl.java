package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.core.Stats;
import id.ac.ui.cs.advprog.workatwebservice.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatsServiceImpl implements StatsService{

    @Autowired
    private StatsRepository statsRepository;

    @Override
    public Iterable<Stats> getListStats() {
        return statsRepository.findAll();
    }

    @Override
    public Stats createStats(String id) {
        Stats newStats = new Stats(id, 0, 0, 0);
        statsRepository.save(newStats);
        return newStats;
    }

    @Override
    public Stats updateStats(String id, GameObject gameObject) {
        Stats updatedStats = statsRepository.getById(id);
        int avg = updatedStats.getAverageAttempt();

        int totalMenang = updatedStats.getTotalMenang();
        int totalKalah = updatedStats.getTotalKalah();

        updatedStats.setAverageAttempt((avg * (totalKalah + totalMenang) + gameObject.getAttemptAmount())
                / (totalKalah + totalMenang + 1));

        if (gameObject.isFinalState()){
            updatedStats.setTotalMenang(totalMenang + 1);
        }
        else {
            updatedStats.setTotalKalah(totalKalah + 1);
        }

        return updatedStats;
    }

    @Override
    public Optional<Stats> getStats(String id) {
        return statsRepository.findById(id);
    }
}
