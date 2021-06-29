package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;

import java.util.Comparator;

class XAxisComparator implements Comparator<Position> {
    /**
     * compares the x-value of two positions
     */
    public int compare(Position o1, Position o2) {
        return (int) Math.signum(o1.x - o2.x);
    }
}
