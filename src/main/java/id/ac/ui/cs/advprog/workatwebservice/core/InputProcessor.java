package id.ac.ui.cs.advprog.workatwebservice.core;

import net.minidev.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InputProcessor{
    ArrayList<String> wordList = new ArrayList<>();
    String answer;
    int attempts = 5;

    public InputProcessor(String answer){
        this.answer = answer;
        // test answers
        wordList.add("WORDS");
        wordList.add("ARRAY");
        wordList.add("INPUT");
        wordList.add("PARSE");
        wordList.add("STATE");
        wordList.add("FALSE");
    }

    public List<String> getWordList() {
        return wordList;
    }

    public JSONObject checkIfInputIsAnswer(String input){
        JSONObject JSONResponse = new JSONObject();

        if (input.length() != 5){
            JSONResponse.put("error", "input must have exactly 5 letters");
            return JSONResponse;
        }
        else if (!wordList.contains(input)){
            JSONResponse.put("error", "input is invalid");
            return JSONResponse;
        }
        else if (attempts < 0){
            JSONResponse.put("error", "ran out of attempts");
            return JSONResponse;
        }
        else {
            List<Character> wordChars = input.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
            List<Character> answerChars = answer.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
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
            attempts -= 1;

            JSONResponse.put("letterStates", res);
            JSONResponse.put("isCorrect", status);
            JSONResponse.put("attemptsleft", attempts);
            return JSONResponse;
        }
    }

}