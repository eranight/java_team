package ru.atom.gameserver.gsession.filling.action;

import ru.atom.gameserver.gsession.common.Tickable;
import ru.atom.gameserver.gsession.filling.geometry.Bar;

public interface Movable extends Positionable, Tickable {
    Bar move(Direction direction, long time);

    enum Direction {
        UP, DOWN, RIGHT, LEFT, IDLE
    }
}
