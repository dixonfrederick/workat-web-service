package id.ac.ui.cs.advprog.workatwebservice.repository;

import id.ac.ui.cs.advprog.workatwebservice.core.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.core.helper.RandomString;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class GameRepository {
    private Map<String, GameObject> games = new HashMap<>();

    public GameObject getById(String id) {
        return games.get(id);
    }

    public GameObject create(GameObject game) {
        games.put(game.getGameId(), game);
        
        return game;
    }
}