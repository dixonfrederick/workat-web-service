package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.model.Stats;

import java.util.Optional;

public interface StatsService {
    Iterable<Stats> getListStats();

    Stats createStats(String id);

    Stats updateStats(String id, GameObject gameObject);

    Optional<Stats> getStats(String id);
}
