package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

/**
 * Player ship class
 */
public class Ship extends AIControlled implements MovingGameObject {
    Random random;
    ListIterator<Position> patternIterator;
    MovementPatterns movementPatterns;
    ArrayList<Position> pattern;
    private Position targetCoordinates;
    //  int number = gameView.playSound("ship.wav", true);
    int hash;


    /**
     * creates Ship with basic parameters
     */
    public Ship(GameView gameView) {
        super(gameView);
        random = new Random();
        hitBox.width = 90;
        hitBox.height = 75;
        movementPatterns = new MovementPatterns();
        this.pattern = movementPatterns.getPattern("zigzag");
        this.patternIterator = pattern.listIterator();
        this.targetCoordinates = new Position(2, 2);
        this.health = 200;
        this.speedInPixel = 2;
        this.position = new Position(random.nextInt(GameView.WIDTH), random.nextInt(GameView.HEIGHT-100));
        this.amountOfAmmo = 100;
        this.width = (int) (24 * size);
        this.height = (int) (9 * size);
        this.size = 2.5;
        this.rotation = -45;
        flyFromLeftToRight = true;
        this.hash = hashCode();
    }

    /**
     * @ draws the Alien on the canvas
     */
    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("CombatShip.png", position.x, position.y, size, rotation);
    }


    /**
     * updates status of Object
     */
    @Override
    public void updateStatus() {
        if (gameView.timerExpired("plasmatimer" + hash, "plasmatimer" + hash)) {
            gamePlayManager.shootPlasmaBall(position);
            gameView.setTimer("plasmatimer" + hash, "plasmatimer" + hash, 1000);
        }

    }

    /**
     * moves Object to the right until on window border, then changes direction
     */
    @Override
    public void updatePosition() {

        double distance = position.distance(targetCoordinates);
        if (distance <= speedInPixel) {

            if (patternIterator.hasNext()) {
                Position pos = patternIterator.next();
                targetCoordinates = pos;
                System.out.print(pos);
            } else {


                pattern = movementPatterns.getPattern("zigzag");
                patternIterator = pattern.listIterator();

            }
        } else {
            position.right((targetCoordinates.x - position.x) / distance * speedInPixel);
            position.down((targetCoordinates.y - position.y) / distance * speedInPixel);
        }


    }

    /**
     * Moves the object slightly to the right.
     *
     * @param speedInPixel sets the amount of acceleration, the object gets
     */
    @Override
    public void updatePosition(double speedInPixel) {


    }


    /**
     * generates a {@link Shot} projectile on position of alien
     */
    public void shoot() {

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


    private int calculateRemainingHealth(int damage) {
        return (health - damage);

    }


}


