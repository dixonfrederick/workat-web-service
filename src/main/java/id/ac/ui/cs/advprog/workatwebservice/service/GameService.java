package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.core.GameObject;

public interface GameService {

    GameObject createGame(GameObject gameObject);

    GameObject viewGame(String id);

    //GameObject submitAnswer();
}
