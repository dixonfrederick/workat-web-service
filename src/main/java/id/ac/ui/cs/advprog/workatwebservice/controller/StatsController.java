package id.ac.ui.cs.advprog.workatwebservice.controller;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.core.Stats;
import id.ac.ui.cs.advprog.workatwebservice.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/stats")
public class StatsController {
    @Autowired
    private StatsService statsService;

    @GetMapping(produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Iterable<Stats>> getListStats() {
        return ResponseEntity.ok(statsService.getListStats());
    }

    @PostMapping(produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Stats> createStats(String id) {
        return ResponseEntity.ok(statsService.createStats(id));
    }

    @PutMapping(path = "/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Stats> updateStats(@PathVariable(value = "id") String id, GameObject gameobject) {
        return ResponseEntity.ok(statsService.updateStats(id, gameobject));
    }

    @GetMapping(path = "/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Optional<Stats>> getStats(@PathVariable(value = "id") String id) {
        Optional<Stats> stats = statsService.getStats(id);
        if (stats.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(stats);
    }
}
