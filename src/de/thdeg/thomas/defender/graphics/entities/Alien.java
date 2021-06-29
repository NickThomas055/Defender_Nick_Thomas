package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;


import java.util.Random;


/**
 * creates Alien objects for the player to fight
 */
public class Alien extends AIControlled implements MovingGameObject {
    Farmer followMe;
    Random random;
    private final String objectID;
    private Position targetCoordinates;
    private State state;


    private enum State {
        ESCAPE, GRAB,
    }

    /**
     * creates Alien with basic parameters
     */
    public Alien(GameView gameView, Farmer followMe) {
        super(gameView);
        random = new Random();
        this.followMe = followMe;
        this.state = State.GRAB;
        hitBox.width = 60;
        hitBox.height = 40;
        this.health = 200;
        this.speedInPixel = 0.5;
        this.position = new Position(random.nextInt(1600) + 1, random.nextInt((GameView.HEIGHT - 500)) + 1);
        this.objectID = "Alien" + (int) position.x + (int) position.y;
        this.amountOfAmmo = 100;
        this.width = (int) (24 * size);
        this.height = (int) (9 * size);
        this.size = 2;
        this.rotation = 0;
        flyFromLeftToRight = true;

        int number = gameView.playSound("alienwav.wav", false);
    }

    /**
     * draws the Alien on the canvas
     */
    @Override
    public void addToCanvas() {

        gameView.addImageToCanvas("Alien.png", position.x, position.y, size, rotation);
        //  gameView.addRectangleToCanvas(hitBox.x, hitBox.y, hitBox.width, hitBox.height, 2, false, Color.red);
        if (state == State.ESCAPE) {
            gameView.addImageToCanvas("Beam.png", position.x, position.y+60, size, rotation);

        }
    }


    /**
     * Moves the object slightly to the right.
     *
     * @param speedInPixel sets the amount of acceleration, the object gets
     */
    @Override
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


        if (state == State.ESCAPE) {
            targetCoordinates = new Position(position.x, 0);
            double distance = position.distance(targetCoordinates);
            if (distance >= speedInPixel) {
                position.right((targetCoordinates.x - position.x) / distance * speedInPixel);
                position.down((targetCoordinates.y - position.y) / distance * speedInPixel);
            }
        } else if (state == State.GRAB) {

            targetCoordinates = followMe.getPosition().clone();
            double distance = position.distance(targetCoordinates);
            if (distance >= speedInPixel) {
                position.right((targetCoordinates.x - position.x) / distance * speedInPixel);
                position.down((targetCoordinates.y - position.y) / distance * speedInPixel);
            }

        }
    }


    /**
     * generates a {@link Shot} projectile on position of alien
     */
    public void shoot() {

    }

    @Override
    public void reactToCollision(CollidableGameObject collidableGameObject) {
        if (collidableGameObject.getClass() == Shot.class) {
            followMe.makeFarmerFall();
        }
        state = State.ESCAPE;


    }


    /**
     * updates status of Object
     */
    @Override
    public void updateStatus() {
        if (position.y <= 1) {
            gamePlayManager.destroyAliens(this);
            gamePlayManager.gameOver = true;
        }


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

    /**
     * adapts position relative to player
     *
     * @param adaptX
     * @param adaptY
     */
    @Override
    public void adaptPosition(double adaptX, double adaptY) {
        position.x += adaptX * 1.5;

    }

}








