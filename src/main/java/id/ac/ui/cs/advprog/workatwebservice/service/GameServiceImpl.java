package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.core.InputProcessor;
import id.ac.ui.cs.advprog.workatwebservice.core.answer.Result;
import id.ac.ui.cs.advprog.workatwebservice.core.helper.RandomString;
import id.ac.ui.cs.advprog.workatwebservice.repository.GameRepository;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public GameObject createGame(GameObject gameObject){
        gameObject.setGameId(RandomString.generateRandomString(32));
        gameObject.setAttemptAmount(0);
        gameObject.setCorrectWord("TESTS");  // TODO: Get from words repository

        gameRepository.save(gameObject);

        return gameObject;
    }

    @Override
    public GameObject viewGame(String id) {
        return gameRepository.findByGameId(id);
    }

    @Override
    public Result submitAnswer(String gameId, String input) {
        GameObject game = gameRepository.findByGameId(gameId);
        InputProcessor inputProcessor = new InputProcessor(game.getCorrectWord());

        return inputProcessor.checkIfInputIsAnswer(input, game);
    }
}
