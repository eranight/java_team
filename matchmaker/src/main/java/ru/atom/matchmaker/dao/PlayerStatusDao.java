package ru.atom.matchmaker.dao;

import org.springframework.data.repository.CrudRepository;
import ru.atom.matchmaker.model.PlayerStatus;

public interface PlayerStatusDao extends CrudRepository<PlayerStatus, Integer> {
}
