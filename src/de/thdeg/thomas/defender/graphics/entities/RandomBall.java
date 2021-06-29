package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;

import java.util.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * randomly moving ball that marks pattern-Iterator's next position with a white ball
 */
public class RandomBall extends AIControlled implements MovingGameObject {
    private Position targetCoordinates;
    Random random;
    ListIterator<Position> patternIterator;
    MovementPatterns movementPatterns;
    ArrayList<Position> pattern;

    /**
     * constructs a RandomBall Object
     *
     * @param gameView
     */
    public RandomBall(GameView gameView) {
        super(gameView);
        random = new Random();
        movementPatterns = new MovementPatterns();
        this.pattern = movementPatterns.getPattern("centered");
        this.patternIterator = pattern.listIterator();
        this.targetCoordinates = new Position(2, 2);
        this.health = 200;
        hitBox.width = 60;
        hitBox.height = 35;
        this.speedInPixel = 4.0;
        this.position = new Position(0, 0);
        this.amountOfAmmo = 100;
        this.width = (int) (24 * size);
        this.height = (int) (9 * size);
        this.size = 50;
        this.rotation = 0;
    }

    /**
     * updates status of Object
     */
    @Override
    public void updateStatus() {

    }


    /**
     * draws the Alien on the canvas
     */
    @Override
    public void addToCanvas() {
        //gameView.addOvalToCanvas(position.x, position.y, size, size, 5, true, Color.yellow);
        //gameView.addOvalToCanvas(targetCoordinates.x, targetCoordinates.y, size, size, 5, true, Color.white);

    }


    /**
     * Moves the object slightly to the right.
     *
     * @param speedInPixel sets the amount of acceleration, the object gets
     */
    @Override
    public void updatePosition(double speedInPixel) {

        if (position.x < targetCoordinates.x) {
            position.right();
        }
        if (position.y < targetCoordinates.y) {
            position.down();
            if (position.x <= 0) {
                flyFromLeftToRight = true;
            }
        }
    }

    @Override
    public void updatePosition() {

        double distance = position.distance(targetCoordinates);
        if (distance <= speedInPixel) {

            if (patternIterator.hasNext()) {
                Position pos = patternIterator.next();
                targetCoordinates = pos;

            } else {

                pattern = movementPatterns.getPattern("centered");
                patternIterator = pattern.listIterator();
            }
        } else {
            position.right((targetCoordinates.x - position.x) / distance * speedInPixel);
            position.down((targetCoordinates.y - position.y) / distance * speedInPixel);
        }


    }
}


