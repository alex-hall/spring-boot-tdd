package com.alex.integration;

import com.alex.user.User;
import com.alex.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    private User createdUser = new User();

    @Before
    public void testSetup() {
        createdUser.setFirstName("Some");
        createdUser.setLastName("Person");
        createdUser.setPhoneNumber("2052222222");

        userRepository.save(createdUser);
    }

    @Test
    public void shouldReturnDataIfFound() throws Exception {
        mockMvc
                .perform(get("/user/" + createdUser.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("{'firstName':'Some', 'lastName': 'Person', 'phoneNumber': '2052222222'}"));
    }

    @Test
    public void shouldReturn404IfNotFound() throws Exception {
        mockMvc
                .perform(get("/user/banana"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldFindUserIfAlreadyExist() throws Exception {
        mockMvc
                .perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createdUser))
                )
                .andExpect(status().isOk())
                .andExpect(content().json("{'firstName':'Some', 'lastName': 'Person', 'phoneNumber': '2052222222'}"));
    }

    @Test
    public void shouldCreateUserIfTheyDoNotExist() throws Exception {
        User someUserNotInTheDatabase = new User();
        someUserNotInTheDatabase.setFirstName("Homer");
        someUserNotInTheDatabase.setLastName("Depot");
        someUserNotInTheDatabase.setPhoneNumber("9119119911");

        mockMvc
                .perform(
                        post("/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(someUserNotInTheDatabase))
                )
                .andExpect(status().isOk())
                .andExpect(content().json("{'firstName':'Homer', 'lastName': 'Depot', 'phoneNumber': '9119119911'}"));
    }
}
