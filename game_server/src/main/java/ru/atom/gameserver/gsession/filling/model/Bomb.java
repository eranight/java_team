package ru.atom.gameserver.gsession.filling.model;

import ru.atom.gameserver.gsession.common.Tickable;
import ru.atom.gameserver.gsession.filling.geometry.Point;

public class Bomb extends AbstractGameObject implements Tickable {
    private int timer = 10;

    public Bomb(Point p1, Point p2) {
        super(p1, p2);
    }

    @Override
    public void tick(long elapsed) {
        timer -= elapsed;
        if (timer <= 0) {
            blowUp();
            isActive = false;
        }
    }

    private void blowUp() {

    }
}
