package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.model.User;

import java.util.Optional;

public interface UserProfileService {
    Iterable<User> getListUser();

    User createUser(User user);

    User updateUser(String id, User user);

    Optional<User> getUser(String id);
}