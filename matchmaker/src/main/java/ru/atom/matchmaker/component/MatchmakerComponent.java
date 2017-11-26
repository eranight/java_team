package ru.atom.matchmaker.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.atom.matchmaker.model.Player;
import ru.atom.matchmaker.service.DatabaseService;
import ru.atom.matchmaker.service.GameService;
import ru.atom.matchmaker.utils.MatchBuilder;

/**
 * Created by Alexandr on 25.11.2017.
 */
@Component
@RequestMapping("matchmaker")
public class MatchmakerComponent {
    private static final Logger logger = LoggerFactory.getLogger(MatchmakerComponent.class);

    @Autowired
    private GameService gameService;
    @Autowired
    private DatabaseService databaseService;

    @Value("${maxplayerscount}")
    private int maxPlayersCount;

    private MatchBuilder currentBuilder;

    @RequestMapping(
            path = "signup",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> signup(@RequestParam("login") String login, @RequestParam("password") String password) {
        logger.info("signup request has been received" + maxPlayersCount);
        //check login
        //check password
        if (!databaseService.checkSignupLogin(login)) {
            databaseService.signUp(login, password);
            return ResponseEntity.ok("ok");
        } else {
            return ResponseEntity.badRequest().body("login is used");
        }
    }

    @RequestMapping(path = "join",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> join(@RequestParam("login") String login, @RequestParam("password") String password) {
        logger.info("join request has been received");
        Player player = databaseService.login(login, password);
        if (player != null) {
            long gameId = processJoinRequest(login);
            return ResponseEntity.ok(String.valueOf(gameId));
        } else {
            return ResponseEntity.badRequest().body("bad login or password");
        }
    }

    private boolean checkValidLogin(String login) {
        return (login != null) && !"".equals(login.trim());
    }

    private long processJoinRequest(String login) {
        long gameId;
        if (currentBuilder == null) {
            gameId = gameService.create(maxPlayersCount);
            databaseService.createGame(gameId);
            currentBuilder = new MatchBuilder(maxPlayersCount, gameId);
            logger.info("game has been created with id=" + gameId);
        } else {
            gameId = currentBuilder.getGameId();
        }
        currentBuilder.putLogin(login);
        gameService.connect(login, currentBuilder.getGameId());
        if (currentBuilder.isReady()) {
            databaseService.addPlayers(currentBuilder.getGameId(), currentBuilder.getLogins());
            gameService.start(currentBuilder.getGameId());
            currentBuilder = null;
        }
        return gameId;
    }
}
