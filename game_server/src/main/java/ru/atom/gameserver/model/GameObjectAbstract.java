package ru.atom.gameserver.model;

import ru.atom.gameserver.action.Positionable;
import ru.atom.gameserver.geometry.Bar;
import ru.atom.gameserver.geometry.Point;

public class GameObjectAbstract implements Positionable {
    protected Bar position;
    protected final int id;
    protected static int nextId = 1;
    protected boolean isActive = true;

    public GameObjectAbstract(Point p1, Point p2) {
        position = new Bar(p1, p2);
        id = nextId++;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bar getPosition() {
        return position;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
