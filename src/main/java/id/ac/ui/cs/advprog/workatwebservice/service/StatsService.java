package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.core.Stats;

public interface StatsService {
    Iterable<Stats> getListStats();

    Stats createStats(String id);

    Stats updateStats(String id, GameObject gameObject);

    Stats getStats(String id);
}
