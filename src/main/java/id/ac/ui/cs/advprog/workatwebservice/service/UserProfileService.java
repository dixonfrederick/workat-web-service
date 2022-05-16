package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.model.User;

public interface UserProfileService {
    Iterable<User> getListUser();

    User createUser(String id);

    User updateUser(String id, GameObject gameObject);

    User getUser(String id);
}