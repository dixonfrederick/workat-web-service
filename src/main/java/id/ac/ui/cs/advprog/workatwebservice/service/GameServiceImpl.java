package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.core.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepo;

    @Override
    public GameObject createGame(GameObject gameObject){
        return gameObject;
    }

    @Override
    public GameObject viewGame(String id) {
        return gameRepo.GetById(id);
    }

    /*
    public GameObject submitAnswer(){
        return
    }
    */
}
