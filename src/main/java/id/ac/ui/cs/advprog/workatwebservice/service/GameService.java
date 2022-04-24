package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.core.GameObject;
import net.minidev.json.JSONObject;

public interface GameService {

    GameObject createGame(GameObject gameObject);

    GameObject viewGame(String id);

    JSONObject submitAnswer(String gameId, String input);
}
