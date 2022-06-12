package id.ac.ui.cs.advprog.workatwebservice.controller;

import id.ac.ui.cs.advprog.workatwebservice.model.GameObject;
import id.ac.ui.cs.advprog.workatwebservice.model.User;
import id.ac.ui.cs.advprog.workatwebservice.core.answer.Result;
import id.ac.ui.cs.advprog.workatwebservice.service.GameService;
import id.ac.ui.cs.advprog.workatwebservice.service.UserProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.client.WebClient;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;
    @MockBean
    private UserProfileService userProfileService;
    @MockBean
    private WebClient.Builder webClientBuilder;

    private GameObject gameObject;
    private User user;

    @BeforeEach
    public void setUp() {
        webClientBuilder.build();
        gameObject = new GameObject();
        user = new User();
    }

    @Test
    void testGetListUser() throws Exception {
        Iterable<User> userList = List.of(user);
        when(userProfileService.getListUser()).thenReturn(userList);

        mockMvc.perform(get("/api/user/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateUser() throws Exception {
        when(userProfileService.createUser(user)).thenReturn(user);

        mockMvc.perform(post("/api/user/")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(user)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateUser() throws Exception {
        userProfileService.createUser(user);
        when(userProfileService.updateUser("0", user)).thenReturn(user);

        mockMvc.perform(put("/api/user/0")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(user)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetUser() throws Exception {
        when(userProfileService.getUser("0")).thenReturn(Optional.ofNullable(user));

        mockMvc.perform(get("/api/user/0")
                        .contentType(MediaType.APPLICATION_JSON).content(Mapper.mapToJson(user)))
                .andExpect(status().isOk());
    }

}
