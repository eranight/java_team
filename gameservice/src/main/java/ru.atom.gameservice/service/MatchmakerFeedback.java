package ru.atom.gameservice.service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MatchmakerFeedback {
    private static final Logger logger = LoggerFactory.getLogger(MatchmakerFeedback.class);

    private static final OkHttpClient client = new OkHttpClient();

    @Value("${url}")
    private String url;
    @Value("${gameover}")
    private String gameOver;

    public void gameOver(long gameId, String winnersLogin) {
        String content = "gameId=" + gameId + "&winner=" + winnersLogin;
        Request request = new Request.Builder()
                .post(RequestBody.create(MediaType.parse("text/plain"), content))
                .url(url + gameOver)
                .build();
        try {
            logger.info("try to send game over request with content=" + content);
            client.newCall(request).execute();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
