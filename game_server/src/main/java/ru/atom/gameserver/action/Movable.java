package ru.atom.gameserver.action;

import ru.atom.gameserver.geometry.Bar;

public interface Movable extends Positionable, Tickable {
    Bar move(Direction direction, long time);

    enum Direction {
        UP, DOWN, RIGHT, LEFT, IDLE
    }
}
