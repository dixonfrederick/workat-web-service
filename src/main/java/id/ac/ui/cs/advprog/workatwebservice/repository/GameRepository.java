package id.ac.ui.cs.advprog.workatwebservice.repository;

import id.ac.ui.cs.advprog.workatwebservice.core.GameObject;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class GameRepository {
    private Map<String, GameObject> gameRepo = new HashMap<>();

    public GameObject GetById(String id) { return gameRepo.get(id);}
}