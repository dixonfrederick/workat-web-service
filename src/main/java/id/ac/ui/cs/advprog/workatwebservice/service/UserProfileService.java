package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.model.User;

import java.util.Optional;

public interface UserProfileService {
    Iterable<User> getListUser();

    User createUser(String id);

    User updateUser(String id, GameObject gameObject);

    Optional<User> getUser(String id);
}