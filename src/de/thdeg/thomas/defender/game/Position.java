package de.thdeg.thomas.defender.game;

import java.util.Objects;

/**
 * Enables the use of {@link #x} and {@link #y} coordinates to display positions in a grid
 */
public class Position implements Cloneable {
    /**
     * x coordinate on grid
     */
    public double x;
    /**
     * y-coordinate on grid
     */
    public double y;

    /**
     * constructor to create a new position
     *
     * @param x x-coordinate of position
     * @param y y-coordinate of position
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * standard constructor for Position
     * sets x and y coordinates to (0,0)
     */
    public Position() {
        this(0, 0);
    }

    /**
     * moves object to the left
     */
    public void left() {
        x -= 1;
        return;
    }

    /**
     * @param pixel amount of pixels that you want to move the object to the left by
     */
    public void left(double pixel) {
        x -= pixel;
    }

    /**
     * moves object to the right
     */
    public void right() {
        x += 1;
    }

    /**
     * @param pixel amount of pixels that you want to move the object to the right by
     */
    public void right(double pixel) {
        x += pixel;
        return;
    }

    /**
     * moves object up
     */
    public void up() {
        y -= 1;
        return;
    }

    /**
     * @param pixel amount of pixels that you want to move the object up by
     */
    public void up(double pixel) {
        y -= pixel;
        return;
    }

    /**
     * moves object down
     */
    public void down() {
        y += 1;
        return;
    }

    /**
     * @param pixel amount of pixels that you want to move the object down by
     */
    public void down(double pixel) {
        y += pixel;
        return;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public String toString() {
        return ("Position(" + ((int) Math.round(this.x)) + "," + ((int) Math.round(this.y)) + ")");
    }

    /**
     * returns if two objects are equal
     *
     * @param o object you want to compare
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position other = (Position) o;
        return this.x == other.x && this.y == other.y;
    }

    /**
     * generates hashCode for object
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);

    }

    /**
     * clones object
     *
     * @return a clone
     */
    @Override
    public Position clone() {
        Position other = null;
        try {
            other = (Position) super.clone();
        } catch (CloneNotSupportedException ignored) {
        }

        return other;
    }

    /**
     * calculates distance between two position
     * @param other
     * @return
     */
    public double distance(Position other){
       return Math.sqrt(Math.pow((x - other.x),2)+Math.pow((y - other.y),2));
    }
}







