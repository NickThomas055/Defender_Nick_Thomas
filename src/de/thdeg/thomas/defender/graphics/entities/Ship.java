package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

/**
 * Player ship class
 */
public class Ship extends AIControlled implements MovingGameObject{
    Random random;
    ListIterator<Position> patternIterator;
    MovementPatterns movementPatterns;
    ArrayList<Position> pattern;
    private Position targetcoordinates;




    /**
     * creates Ship with basic parameters
     */
    public Ship(GameView gameView) {
        super(gameView);
        random = new Random();
        hitBox.width = 90;
        hitBox.height = 75;
        movementPatterns = new MovementPatterns();
        this.pattern = movementPatterns.getRandomPattern();
        this.patternIterator = pattern.listIterator();
        this.targetcoordinates = new Position(2, 2);
        this.health = 200;
        this.speedInPixel = 2;
        this.position = new Position(random.nextInt(GameView.WIDTH - 0) + 0, random.nextInt(GameView.HEIGHT - 0) + 0);
        this.amountOfAmmo = 100;
        this.width = (int) (24 * size);
        this.height = (int) (9 * size);
        this.size = 3;
        this.rotation = -45;
        flyFromLeftToRight = true;
    }

    @Override
    /**@
     * draws the Alien on the canvas
     */
    public void addToCanvas() {
        gameView.addImageToCanvas("CombatShip.png", position.x, position.y, size, rotation);
        //gameView.addRectangleToCanvas(hitBox.x,hitBox.y,hitBox.width, hitBox.height,2,false, Color.red);
    }



    /**
     * updates status of Object
     */
    @Override
    public void updateStatus() {

    }

    /**
     * moves Object to the right until on window border, then changes direction
     */
    @Override
    public void updatePosition() {
        {
            double distance = position.distance(targetcoordinates);
            if (distance <= speedInPixel) {

                if (patternIterator.hasNext()) {
                    Position pos = patternIterator.next();
                    targetcoordinates = pos;
                    System.out.print(pos);
                } else {


                        pattern = movementPatterns.getRandomPattern();
                        patternIterator = pattern.listIterator();

                }
            } else {
                position.right((targetcoordinates.x - position.x) / distance * speedInPixel);
                position.down((targetcoordinates.y - position.y) / distance * speedInPixel);
            }


        }


    }
    @Override
    /**
     * Moves the object slightly to the right.
     * @param speedInPixel sets the amount of acceleration, the object gets
     */
    public void updatePosition(double speedInPixel) {

        if (flyFromLeftToRight) {
            this.position.x += 1 * speedInPixel;
            if (position.x == GameView.WIDTH - 100) {
                flyFromLeftToRight = false;
            }
        } else {
            this.position.x -= 1 * speedInPixel;
            if (position.x == 0) {
                flyFromLeftToRight = true;
            }
        }
    }


    /**
     * generates a {@link Shot} projectile on position of alien
     */
    public void shoot() {

    }

    /**
     * lands an alien spaceship on the ground
     */
    public void land() {

    }

    /**
     * setter method of object
     *
     * @param position sets position of object
     * @see Position
     */
    public void setPosition(Position position) {
        this.position = position;
    }


    private double calculateSpeed() {
        return (speedInPixel * 2);
    }

    private int calculateRemainingHealth(int damage) {
        return (health - damage);

    }


}


