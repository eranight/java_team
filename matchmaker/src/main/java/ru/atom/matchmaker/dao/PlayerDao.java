package ru.atom.matchmaker.dao;

import org.springframework.data.repository.CrudRepository;
import ru.atom.matchmaker.model.Player;

import java.util.Set;

/**
 * Created by Alexandr on 25.11.2017.
 */
public interface PlayerDao extends CrudRepository<Player, Integer> {
    public Player getByLogin(String login);
    public Player getByLoginAndPassword(String login, String password);
    public Set<Player> findByLoginIn(Iterable<String> logins);
}

