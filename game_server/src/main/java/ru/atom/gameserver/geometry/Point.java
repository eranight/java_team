package ru.atom.gameserver.geometry;

import ru.atom.gameserver.action.Collider;

public class Point implements Collider {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean moreOrEquals(Point o) {
        return this.x >= o.getX() && this.y >= o.getY();
    }

    public boolean lessOrEquals(Point o) {
        return this.x <= o.getX() && this.y <= o.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public boolean isColliding(Collider other) {
        if (other instanceof Point) {
            return this.equals(other);
        }
        throw new UnsupportedOperationException();
    }

    public Point move(Point direction) {
        return new Point(x + direction.getX(), y + direction.getY());
    }
}