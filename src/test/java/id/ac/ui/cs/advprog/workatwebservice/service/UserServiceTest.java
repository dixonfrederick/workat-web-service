package id.ac.ui.cs.advprog.workatwebservice.service;

import id.ac.ui.cs.advprog.workatwebservice.core.helper.InputProcessor;
import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.model.User;
import id.ac.ui.cs.advprog.workatwebservice.repository.GameRepository;
import id.ac.ui.cs.advprog.workatwebservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
@EnableAsync
public class UserServiceTest {
    @Mock
    private InputProcessor inputProcessor;

    @Mock
    private UserRepository userRepository;

    @Spy
    @InjectMocks
    private UserProfileServiceImpl userProfileService = new UserProfileServiceImpl();

    private User user;

    private GameObject gameObject;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUserId("0");
        gameObject = new GameObject();
        userRepository.save(user);
    }

    @Test
    void testCreateUser() {
        ReflectionTestUtils.setField(userProfileService, "userRepository", userRepository);
        User createdUser = userProfileService.createUser(user);
        assertEquals(createdUser.getUserId(), user.getUserId());
    }

    @Test
    void testUpdateUser() {
        gameObject.setFinalState(true);
        ReflectionTestUtils.setField(userProfileService, "userRepository", userRepository);
        User updatedUser = userProfileService.updateUser("0", user);
        assertEquals(updatedUser.getUserId(), 1);
    }

    @Test
    void testGetUser() {
        ReflectionTestUtils.setField(userProfileService, "userRepository", userRepository);
        Optional<User> retrievedUser = userProfileService.getUser("0");
        assertEquals(retrievedUser.get().getUserId(), user.getUserId());
    }

}
