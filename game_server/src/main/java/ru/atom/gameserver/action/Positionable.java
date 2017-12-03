package ru.atom.gameserver.action;

import ru.atom.gameserver.entity.GameObject;
import ru.atom.gameserver.geometry.Bar;

public interface Positionable extends GameObject {
    Bar getPosition();
}
