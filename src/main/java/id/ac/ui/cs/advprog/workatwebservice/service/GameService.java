package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.core.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.core.answer.Result;

public interface GameService {

    GameObject createGame(GameObject gameObject);

    GameObject viewGame(String id);

    Result submitAnswer(String gameId, String input);
}
