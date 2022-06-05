package id.ac.ui.cs.advprog.workatwebservice.core.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import id.ac.ui.cs.advprog.workatwebservice.core.answer.Result;
import id.ac.ui.cs.advprog.workatwebservice.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class InputProcessor{
    @Autowired
    private WebClient client;

    @Autowired
    private GameRepository gameRepository;

    private boolean wordIsRegistered(String attempt) {
        try {
            Future<Boolean> wordExists = CompletableFuture.supplyAsync(() -> {
                ObjectMapper mapper = new ObjectMapper();

                Mono<String> response = client
                        .get()
                        .uri("http://WORDS-SERVICE/api/word/" + attempt.toLowerCase())
                        .retrieve()
                        .bodyToMono(String.class);


                String json = response.block();
                try {
                    mapper.readTree(json).path("word");
                    return true;
                } catch (Exception e) {
                    return false;
                }
            });
            return wordExists.get();
        } catch (Exception e) {
            return false;
        }
    };

    public Result checkIfInputIsAnswer(String input, GameObject gameObject){
        Result result = new Result();
        int attempts = gameObject.getAttemptAmount();

        if (input.length() != 5){
            result.setError("Input must have exactly 5 letters");
            return result;
        }
        else if (!wordIsRegistered(input)){
            result.setError("Word is not in dictionary");
            return result;
        }
        else if (attempts >= 5){
            result.setError("Ran out of attempts");
            return result;
        }
        else {
            List<Character> wordChars = input.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
            List<Character> answerChars = gameObject.getCorrectWord().chars().mapToObj(e -> (char) e).collect(Collectors.toList());
            String res = "";
            boolean status;


            for (int i = 0; i < 5; i++) {
                if (wordChars.get(i).equals(answerChars.get(i))) {
                    res += "B";
                    answerChars.set(i, '0');
                } else if (answerChars.contains(wordChars.get(i))) {
                    res += "S";
                } else {
                    res += "N";
                }
            }

            status = res.equals("BBBBB");

            result.setLetterStates(res);
            result.setCorrect(status);
            result.setAttemptAmount(gameObject.getAttemptAmount() + 1);

            gameObject.setAttemptAmount(gameObject.getAttemptAmount() + 1);
            gameRepository.save(gameObject);

            return result;
        }
    }

}