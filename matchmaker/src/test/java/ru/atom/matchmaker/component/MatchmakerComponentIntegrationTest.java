package ru.atom.matchmaker.component;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchmakerComponentIntegrationTest {
//    @Autowired
//    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void signupTest() throws Exception {
        assertThat(this.restTemplate.postForLocation("/matchmaker/signup", "login=admin&password=1234").equals(status().isOk()));

//        mockMvc.perform(post("/matchmaker/signup")
//                .content("login=admin&password=1234")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(status().isOk());
    }

//    @Test
//    public void joinTest() throws Exception {
//        mockMvc.perform(post("/matchmaker/join")
//                .content("login=admin&password=1234")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
//                .andExpect(status().isOk());
//    }
}