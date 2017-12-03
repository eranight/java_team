package ru.atom.gameserver.model;

import ru.atom.gameserver.geometry.Point;

public class Wall extends GameObjectAbstract {
    public Wall(Point p1, Point p2) {
        super(p1, p2);
    }
}
