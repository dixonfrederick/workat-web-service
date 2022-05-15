package id.ac.ui.cs.advprog.workatwebservice.controller;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.model.User;
import id.ac.ui.cs.advprog.workatwebservice.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    private UserProfileService userService;

    @GetMapping(produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Iterable<User>> getListUser() {
        return ResponseEntity.ok(userService.getListUser());
    }

    @PostMapping(produces = {"application/json"})
    @ResponseBody
    public ResponseEntity createUser(String id) {
        return ResponseEntity.ok(userService.createUser(id));
    }

    @PutMapping(path = "/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity updateUser(@PathVariable(value = "id") String id, @RequestBody GameObject gameobject) {
        return ResponseEntity.ok(userService.updateUser(id, gameobject));
    }

    @GetMapping(path = "/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity getUser(@PathVariable(value = "id") String id) {
        User user = userService.getUser(id);
        if (user == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }
}