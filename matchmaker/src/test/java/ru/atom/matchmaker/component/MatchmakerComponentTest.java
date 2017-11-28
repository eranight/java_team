package ru.atom.matchmaker.component;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class MatchmakerComponentTest {

    @Test
    public void signupIncorrectLoginTest() throws Exception {
        MatchmakerComponent matchmakerHandler = new MatchmakerComponent();
        assertThat(matchmakerHandler.signup("", "123").equals(("incorrect login")));
    }

    @Test
    public void signupIncorrectPasswordTest() throws Exception {
        MatchmakerComponent matchmakerHandler = new MatchmakerComponent();
        assertThat(matchmakerHandler.signup("123", "").equals(("incorrect password")));
    }

    @Ignore
    @Test
    public void signupOKTest() throws Exception {
        MatchmakerComponent matchmakerHandler = new MatchmakerComponent();
        assertThat(matchmakerHandler.signup("123", "123").equals(("ok")));
    }

    @Ignore
    @Test
    public void joinTest() throws Exception {
        MatchmakerComponent matchmakerHandler = new MatchmakerComponent();
        assertThat(matchmakerHandler.join("123", "123"));
        assertThat(matchmakerHandler.join("1", "1").equals("bad login or password"));
    }

}
