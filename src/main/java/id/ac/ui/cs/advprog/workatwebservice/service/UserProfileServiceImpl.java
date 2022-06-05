package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.model.User;
import id.ac.ui.cs.advprog.workatwebservice.repository.StatsRepository;
import id.ac.ui.cs.advprog.workatwebservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatsRepository statsRepository;

    @Autowired
    private StatsService statsService;

    @Override
    public Iterable<User> getListUser() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        userRepository.save(user);
        statsService.createStats(user.getUserId());
        return user;
    }

    @Override
    public User updateUser(String id, User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> getUser(String id) {
        return userRepository.findById(id);
    }
}
