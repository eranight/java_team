package ru.atom.gameserver.gsession.filling.action;

import ru.atom.gameserver.gsession.filling.entity.GameObject;
import ru.atom.gameserver.gsession.filling.geometry.Bar;

public interface Positionable extends GameObject {
    Bar getPosition();
}
