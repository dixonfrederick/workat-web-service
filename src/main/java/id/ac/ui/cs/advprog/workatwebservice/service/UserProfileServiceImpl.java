package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.model.User;
import id.ac.ui.cs.advprog.workatwebservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> getListUser() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(String id) {
        User newUser = new User('', '');
        userRepository.save(newUser);
        return newUser;
    }

    @Override //SAMPE SINI
    public User updateUser(String id, GameObject gameObject) {
        User updatedUser = userRepository.getById(id);
        return updatedUser;
    }

    @Override
    public User getUser(String id) {
        return userRepository.findById(Integer.parseInt(id));
    }
}
