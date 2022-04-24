package id.ac.ui.cs.advprog.workatwebservice.core;

import net.minidev.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import id.ac.ui.cs.advprog.workatwebservice.core.answer.Result;

public class InputProcessor{
    ArrayList<String> wordList = new ArrayList<>();
    String answer;

    public InputProcessor(String answer){
        this.answer = answer;
        // test answers
        wordList.add("WORDS");
        wordList.add("ARRAY");
        wordList.add("INPUT");
        wordList.add("PARSE");
        wordList.add("STATE");
        wordList.add("FALSE");
        wordList.add("TESTS");
    }

    public List<String> getWordList() {
        return wordList;
    }

    public Result checkIfInputIsAnswer(String input, GameObject gameObject){
        Result result = new Result();
        int attempts = 5 - gameObject.getJumlahAttempt();

        if (input.length() != 5){
            result.setError("input must have exactly 5 letters");
            return result;
        }
        else if (!wordList.contains(input)){
            result.setError("input is invalid");
            return result;
        }
        else if (attempts <= 0){
            result.setError("ran out of attempts");
            return result;
        }
        else {
            List<Character> wordChars = input.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
            List<Character> answerChars = answer.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
            String res = "";
            boolean status;

            gameObject.setJumlahAttempt(gameObject.getJumlahAttempt() + 1);

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
            result.setAttemptsLeft(5 - gameObject.getJumlahAttempt());

            return result;
        }
    }

}