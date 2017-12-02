package ru.atom.gameserver.action;

import ru.atom.gameserver.geometry.Point;

public interface Movable extends Positionable, Tickable {
    Point move(Direction direction, long time);

    enum Direction {
        UP, DOWN, RIGHT, LEFT, IDLE
    }
}
