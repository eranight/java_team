package ru.atom.matchmaker.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotEquals;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchMakerServiceIntegrationTest {

    @MockBean
    private GameService remoteService;

    @Test
    public void test1() {
        assertNotEquals(-1, remoteService.create(4));
    }

}
