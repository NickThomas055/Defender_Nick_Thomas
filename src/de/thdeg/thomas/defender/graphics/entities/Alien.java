package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;


import java.util.Random;


/**
 * creates Alien objects for the player to fight
 */
public class Alien extends AIControlled implements MovingGameObject{
    Random random;
    private final String objectID;

    /**
     * creates Alien with basic parameters
     */
    public Alien(GameView gameView) {
        super(gameView);
        random = new Random();
        hitBox.width = 60;
        hitBox.height = 40;
        this.health = 200;
        this.speedInPixel = 1;
        this.position = new Position(random.nextInt(GameView.WIDTH - 0) + 0, random.nextInt(GameView.HEIGHT - 0) + 0);
        this.objectID = "Alien" + (int) position.x + (int) position.y;
        this.amountOfAmmo = 100;
        this.width = (int) (24 * size);
        this.height = (int) (9 * size);
        this.size = 2;
        this.rotation = 0;
        flyFromLeftToRight = true;
    }

    @Override
    /**
     * draws the Alien on the canvas
     */
    public void addToCanvas() {

        gameView.addImageToCanvas("Alien.png", position.x, position.y, size, rotation);
      //  gameView.addRectangleToCanvas(hitBox.x, hitBox.y, hitBox.width, hitBox.height, 2, false, Color.red);
    }



    @Override
    /**
     * Moves the object slightly to the right.
     *
     * @param speedInPixel sets the amount of acceleration, the object gets
     */
    public void updatePosition(double speedInPixel) {

        if (flyFromLeftToRight) {
            this.position.x += 1 * speedInPixel;
            if (position.x >= GameView.WIDTH - 250) {
                flyFromLeftToRight = false;
            }
        } else {
            this.position.x -= 1 * speedInPixel;
            if (position.x <= 0) {
                flyFromLeftToRight = true;
            }
        }
    }

    /**
     * moves the object slightly to the right, changes direction when on window border
     */
    @Override
    public void updatePosition() {

        if (flyFromLeftToRight) {
            this.position.x += 1;
            if (position.x == GameView.WIDTH - 50) {
                flyFromLeftToRight = false;
            }
        } else {
            this.position.x -= 1;
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
     * updates status of Object
     */
    @Override
    public void updateStatus() {

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








