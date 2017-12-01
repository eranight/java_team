package ru.atom.gameservice.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.atom.gameservice.service.MatchmakerFeedback;

@RestController
@RequestMapping("game")
public class HttpRequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestHandler.class);

    private static long idGenerator;

    @Autowired
    private MatchmakerFeedback matchmakerFeedback;

    @RequestMapping(
            path = "create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> create(@RequestParam("playerCount") int playerCount) {
        logger.info("create request has been received with playerCount=" + playerCount);
        logger.info("new id = " + (++idGenerator));
        return ResponseEntity.ok().body(String.valueOf(idGenerator));
    }

    @RequestMapping(
            path = "start",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> start(@RequestParam("gameId") long gameId) {
        logger.info("start request with gameId = " + gameId);
        return ResponseEntity.ok().body(String.valueOf(gameId));
    }

}
