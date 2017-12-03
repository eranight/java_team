package ru.atom.gameserver.gsession.filling.model;

import ru.atom.gameserver.gsession.filling.action.Positionable;
import ru.atom.gameserver.gsession.filling.geometry.Bar;
import ru.atom.gameserver.gsession.filling.geometry.Point;

public class AbstractGameObject implements Positionable {
    protected Bar position;
    protected final int id;
    protected static int nextId = 1;
    protected boolean isActive = true;

    public AbstractGameObject(Point p1, Point p2) {
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
