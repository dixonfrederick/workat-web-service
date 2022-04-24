package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.core.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepo;

    public GameObject createGame(GameObject gameObject){
        return gameObject;
    }

    public GameObject viewGame(String id) {
        GameObject gameObject = gameRepo.GetById(id);
        return gameObject;
    }

    /*
    public GameObject submitAnswer(){
        return
    }
    */
}
