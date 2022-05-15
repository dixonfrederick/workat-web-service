package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.model.User;

public interface UserProfileService {
    Iterable<User> getListUser();

    Stats createUser(String id);

    Stats updateUser(String id, GameObject gameObject);

    Stats getUser(String id);
}