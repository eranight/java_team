package ru.atom.matchmaker.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.atom.matchmaker.model.Player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseServiceIntegrationTest {

    @MockBean
    private DatabaseService databaseService;

    private Player player;

    @Before
    public void createPlayer() {
        databaseService.signUp("user", "1234");
        player = databaseService.login("user", "1234");
    }

    @Test
    public void checkSignupLogin() throws Exception {
        assertTrue(databaseService.checkSignupLogin("user"));
        assertFalse(databaseService.checkSignupLogin("user2"));
    }

    @Test
    public void login() throws Exception {
        assertEquals(player.getLogin(), "user");
        assertEquals(player.getPassword(), "1234");
        assertEquals(player.getStatus(), 2);
    }

    @Test
    public void logout() throws Exception {
        assertEquals(player.getLogin(), "user");
        assertEquals(player.getPassword(), "1234");
        assertEquals(player.getStatus(), 1);
    }
}