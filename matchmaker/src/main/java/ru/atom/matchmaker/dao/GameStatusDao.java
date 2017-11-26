package ru.atom.matchmaker.dao;

import org.springframework.data.repository.CrudRepository;
import ru.atom.matchmaker.model.GameStatus;

public interface GameStatusDao extends CrudRepository<GameStatus, Integer> {
}
