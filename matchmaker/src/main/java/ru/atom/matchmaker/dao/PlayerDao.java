package ru.atom.matchmaker.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.atom.matchmaker.model.Player;
import ru.atom.matchmaker.model.PlayerStatus;

import java.util.Set;

/**
 * Created by Alexandr on 25.11.2017.
 */
@Repository
public interface PlayerDao extends CrudRepository<Player, Integer> {

    Player getByLogin(String login);

    Player getByLoginAndPassword(String login, String password);

    Set<Player> findByLoginIn(Iterable<String> logins);

    Set<Player> findTop10ByOrderByWinsDesc();

    Set<Player> findByStatusEquals(PlayerStatus playerStatus);

    @Modifying
    @Query("UPDATE Player p SET p.status = :nextStatus WHERE p.status = :prevStatus")
    int setPlayerStatusWhere(@Param("nextStatus") PlayerStatus nextPlayerStatus,
                             @Param("prevStatus") PlayerStatus prevPlayerStatus);

}