package com.alex.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldReturnDataIfFound() throws Exception {
        mockMvc
                .perform(get("/user/1" ))
                .andExpect(status().isOk())
                .andExpect(content().json("{'firstName':'Some', 'lastName': 'Person', 'phoneNumber': '2052222222'}"));
    }
}
