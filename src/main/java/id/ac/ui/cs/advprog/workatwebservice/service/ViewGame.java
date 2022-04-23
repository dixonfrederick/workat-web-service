package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.core.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ViewGame {

    @Autowired
    private GameRepository gameRepo;

    public GameObject returnGameObject(GameObject gameObject){
        return gameObject;
    }
}
