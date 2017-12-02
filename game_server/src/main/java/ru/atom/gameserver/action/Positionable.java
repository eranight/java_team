package ru.atom.gameserver.action;

import ru.atom.gameserver.entity.GameObject;
import ru.atom.gameserver.geometry.Point;

public interface Positionable extends GameObject {
    Point getPosition();
}
