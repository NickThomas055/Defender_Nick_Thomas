package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;

import java.util.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * randomly moving ball that marks patternIterator's next position with a white ball
 */
public class RandomBall extends AIControlled implements MovingGameObject {
    private Position targetcoordinates;
    Random random;
    List squarepositions;
    List zigzagositions;
    List randompattern;
    ArrayList<Position> square;
    ArrayList<Position> zigZag;
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
        this.squarepositions = List.of(new Position(30, 30), new Position(930, 30), new Position(930, 510), new Position(30, 510));
        this.zigzagositions = List.of(new Position(300, 200), new Position(400, 340), new Position(500, 200), new Position(600, 340), new Position(700, 200), new Position(800, 340));
        square = new ArrayList<>(squarepositions);
        zigZag = new ArrayList<>(zigzagositions);
        movementPatterns = new MovementPatterns();
        this.pattern = movementPatterns.getPattern("square");
        this.patternIterator = pattern.listIterator();
        this.targetcoordinates = new Position(2, 2);
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
     * fires player weapon
     */


    @Override
    /**
     * draws the Alien on the canvas
     */
    public void addToCanvas() {
        gameView.addOvalToCanvas(position.x, position.y, size, size, 5, true, Color.yellow);
        gameView.addOvalToCanvas(targetcoordinates.x, targetcoordinates.y, size, size, 5, true, Color.white);

    }

    private enum Status {
        STANDARD, DAMAGED, EXPLODING, EXPLODED
    }

    @Override
    /**
     * Moves the object slightly to the right.
     *
     * @param speedInPixel sets the amount of acceleration, the object gets
     */
    public void updatePosition(double speedInPixel) {

        if (position.x < targetcoordinates.x) {
            position.right();
        }
        if (position.y < targetcoordinates.y) {
            position.down();
            if (position.x <= 0) {
                flyFromLeftToRight = true;
            }
        }
    }

    @Override
    public void updatePosition() {

        double distance = position.distance(targetcoordinates);
        if (distance <= speedInPixel) {

            if (patternIterator.hasNext()) {
                Position pos = patternIterator.next();
                targetcoordinates = pos;
                System.out.print(pos);
            } else {

                if (pattern == movementPatterns.getPattern("square")) {
                    pattern = movementPatterns.getPattern("zigzag");
                    patternIterator = pattern.listIterator();
                } else {
                    pattern = movementPatterns.getPattern("square");
                    patternIterator = pattern.listIterator();
                }
            }
        } else {
            position.right((targetcoordinates.x - position.x) / distance * speedInPixel);
            position.down((targetcoordinates.y - position.y) / distance * speedInPixel);
        }


    }
}


