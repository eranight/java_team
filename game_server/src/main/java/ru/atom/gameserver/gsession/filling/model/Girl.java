package ru.atom.gameserver.gsession.filling.model;

import ru.atom.gameserver.gsession.filling.action.Movable;
import ru.atom.gameserver.gsession.filling.geometry.Bar;
import ru.atom.gameserver.gsession.filling.geometry.Point;

public class Girl extends AbstractGameObject implements Movable {
    private int speed = 10;

    public Girl(Point p1, Point p2) {
        super(p1, p2);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public Bar move(Direction direction, long time) {
        Point vectorOfMove;
        switch (direction) {
            case UP:
                vectorOfMove = new Point(0, speed * (int) time);
                break;
            case DOWN:
                vectorOfMove = new Point(0, -speed * (int) time);
                break;
            case RIGHT:
                vectorOfMove = new Point(speed * (int) time, 0);
                break;
            case LEFT:
                vectorOfMove = new Point(-speed * (int) time, 0);
                break;
            default:
                vectorOfMove = new Point(0, 0);
        }
        position = position.move(vectorOfMove);
        return position;
    }

    @Override
    public void tick(long elapsed) {

    }
}
