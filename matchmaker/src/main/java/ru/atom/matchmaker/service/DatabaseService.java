package ru.atom.matchmaker.service;

import org.hibernate.validator.constraints.NotEmpty;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.atom.matchmaker.dao.GameDao;
import ru.atom.matchmaker.dao.GameStatusDao;
import ru.atom.matchmaker.dao.PlayerDao;
import ru.atom.matchmaker.dao.PlayerStatusDao;
import ru.atom.matchmaker.model.Game;
import ru.atom.matchmaker.model.GameStatus;
import ru.atom.matchmaker.model.Player;
import ru.atom.matchmaker.model.PlayerStatus;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Alexandr on 25.11.2017.
 */
@Service
public class DatabaseService {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);

    @Autowired
    private GameDao gameDao;
    @Autowired
    private GameStatusDao gameStatusDao;
    @Autowired
    private PlayerDao playerDao;
    @Autowired
    private PlayerStatusDao playerStatusDao;

    @Transactional
    public boolean checkSignupLogin(@NotNull String login) {
        logger.info("check sign up");
        return playerDao.getByLogin(login) != null;
    }

    @Transactional
    public void signUp(@NotNull String login, @NotNull String password) {
        logger.info("sign up begin");
        Player player = new Player();
        player.setLogin(login)
                .setPassword(password)
                .setStatus(playerStatusDao.findOne(1))
                .setWins(0);
        playerDao.save(player);
        logger.info("sign up end");
    }

    @Nullable
    @Transactional
    public Player login(@NotNull String login, @NotNull String password) {
        Player player = playerDao.getByLoginAndPassword(login, password);
        if (player != null) {
            player.setStatus(playerStatusDao.findOne(2));
            playerDao.save(player);
        }
        return player;
    }

    @Transactional
    public void logout(@NotNull Player player) {
        player.setStatus(playerStatusDao.findOne(1));
        playerDao.save(player);
    }

    @Transactional
    public void createGame(long gameId) {
        Game game = new Game();
        game.setId(gameId).setStatus(gameStatusDao.findOne(1));
        gameDao.save(game);
    }

    @Transactional
    public void addPlayers(long gameId, Set<String> logins) {
        Set<Player> players =  new HashSet<>(playerDao.findByLoginIn(logins));
        Game game = gameDao.findOne(gameId);
        game.setStatus(gameStatusDao.findOne(2)).setPlayers(players);
        gameDao.save(game);
        logger.info("insert game with id=");
    }
}
