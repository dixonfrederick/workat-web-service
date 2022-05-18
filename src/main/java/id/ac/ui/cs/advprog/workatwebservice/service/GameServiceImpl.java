package id.ac.ui.cs.advprog.workatwebservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.core.InputProcessor;
import id.ac.ui.cs.advprog.workatwebservice.core.answer.Result;
import id.ac.ui.cs.advprog.workatwebservice.core.helper.RandomString;
import id.ac.ui.cs.advprog.workatwebservice.repository.GameRepository;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private WebClient client;

    @Autowired
    private InputProcessor inputProcessor;

    @Override
    public GameObject createGame(GameObject gameObject){
        Future<String> randomWord = CompletableFuture.supplyAsync(() -> {
            ObjectMapper mapper = new ObjectMapper();

            Mono<String> response = client
                    .get()
                    .uri("http://WORDS-SERVICE/api/word")
                    .retrieve()
                    .bodyToMono(String.class);

            String json = response.block();
            try {
                JsonNode root = mapper.readTree(json);
                return root.path("word").asText().toUpperCase();
            } catch (Exception e) {
                System.out.println("HERE");
                return "";
            }
        });

        try {
            gameObject.setGameId(RandomString.generateRandomString(32));
            gameObject.setAttemptAmount(0);
            gameObject.setCorrectWord(randomWord.get());

            CompletableFuture.runAsync(() -> {
                gameRepository.save(gameObject);
            });

            return gameObject;
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }
    };

    @Override
    public GameObject viewGame(String id) {
        return gameRepository.findByGameId(id);
    }

    @Override
    public Result submitAnswer(String gameId, String input) {
        GameObject game = gameRepository.findByGameId(gameId);

        return inputProcessor.checkIfInputIsAnswer(input, game);
    }
}
