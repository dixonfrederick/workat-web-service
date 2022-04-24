package id.ac.ui.cs.advprog.workatwebservice.controller;

import id.ac.ui.cs.advprog.workatwebservice.core.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.service.GameService;
import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/game")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping(produces = {"application/json"})
    @ResponseBody
    public ResponseEntity createGame(@RequestBody GameObject gameObject) {
        return ResponseEntity.ok(gameService.createGame(gameObject));
    }

    @GetMapping(path = "/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity viewGame(@PathVariable(value = "id") String id) {
        GameObject viewGameObject = gameService.viewGame(id);
        if (viewGameObject == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(viewGameObject);
    }

    @PostMapping(path = "/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity submitAnswer(@RequestBody String gameId, @RequestBody String answer) {
        JSONObject response = gameService.submitAnswer(gameId, answer);
        return ResponseEntity.ok(response);
    }
}
