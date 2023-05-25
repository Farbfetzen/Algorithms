package farbfetzen.algorithms.geometry;

import java.util.Objects;

public class Vector2 implements Comparable<Vector2> {

    private double x;
    private double y;

    public Vector2(){
        this.x = 0;
        this.y = 0;
    }

    public Vector2(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public float getXf() {
        return (float) x;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public float getYf() {
        return (float) y;
    }

    public void setY(final double y) {
        this.y = y;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof final Vector2 otherVector) {
            return Double.compare(x, otherVector.x) == 0 && Double.compare(y, otherVector.y) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Sort Vectors in ascending order. First sort by x and break ties by sorting y.
     */
    @Override
    public int compareTo(final Vector2 other) {
        final var compareX = Double.compare(x, other.x);
        return compareX != 0 ? compareX : Double.compare(y, other.y);
    }

    @Override
    public String toString() {
        return "Vector2{x=" + x + ", y=" + y + '}';
    }

}
