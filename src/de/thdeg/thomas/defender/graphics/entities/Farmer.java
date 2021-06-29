package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * human that aliens try to abduct and you have to defend
 */
public class Farmer extends CollidingGameObject implements MovingGameObject {


    private enum State {
        FALL, FOLLOW, SIT
    }

    private Position targetCoordinates;
    private State state;
    private boolean hasObjectToFollow;

    Random random;
    CollidableGameObject followMe;


    /**
     * constructs a Farmer Object
     *
     * @param gameView             responsible for visuals
     * @param collidableGameObject all possible objects that could collide with the farmer
     */
    public Farmer(GameView gameView, ArrayList<CollidableGameObject> collidableGameObject) {
        super(gameView, collidableGameObject);
        random = new Random();
        this.state = State.SIT;
        hitBox.width = 60;
        hitBox.height = 35;
        this.speedInPixel = 3.5;
        this.position = new Position(random.nextInt(1200) + 1, random.nextInt((GameView.HEIGHT - 130) - 400) + 400);
        this.width = (int) (24 * size);
        this.height = (int) (9 * size);
        this.size = 1.5;
        this.rotation = 0;
    }

    /**
     * sets a new object the farmer has to follow
     *
     * @param followMe tells farmer to follow this object
     */
    public void setFollowMe(CollidableGameObject followMe) {
        this.followMe = followMe;
    }

    @Override
    protected void updateHitBoxPosition() {
        this.hitBox.x = (int) this.position.x;
        this.hitBox.y = (int) this.position.y + 10;

    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        state = State.FOLLOW;
        setFollowMe(otherObject);

    }

    /**
     * sets farmer state to Fall
     */
    public void makeFarmerFall() {
        this.state = State.FALL;

    }

    /**
     * updates status of Object
     */
    @Override
    public void updateStatus() {
        if (hasObjectToFollow) {
                if (followMe.getClass() == Chopper.class && position.y >= GameView.HEIGHT - 200) {
                    state = State.SIT;
                }
        }
    }


    /**
     * draws the Alien on the canvas
     */
    @Override
    public void addToCanvas() {


        if (state == State.FOLLOW) {
            gameView.addImageToCanvas("farmer.png", position.x, position.y, size, 0);
            if (followMe.getClass() == Chopper.class) {
                gameView.addLineToCanvas(position.x + 25, position.y + 2, followMe.position.x + 45, followMe.position.y + 45, 2, Color.BLACK);
            }
        } else {
            gameView.addImageToCanvas("farmer.png", position.x, position.y, size, 0);
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
     * adapts position relative to player
     *
     * @param adaptX adapts x-position by x amount
     * @param adaptY adapts y-position by x amount
     */
    @Override
    public void adaptPosition(double adaptX, double adaptY) {
        position.x += adaptX * 1.5;

    }

    @Override
    public void updatePosition() {

        if (state == State.FALL) {
            hasObjectToFollow = false;
            targetCoordinates = new Position(position.x, GameView.HEIGHT - 130);
            double distance = position.distance(targetCoordinates);
            speedInPixel = 0.5;
            if (distance >= speedInPixel) {
                position.right((targetCoordinates.x - position.x) / distance * speedInPixel);
                position.down((targetCoordinates.y - position.y) / distance * speedInPixel);
            }
        } else if (state == State.FOLLOW) {
            hasObjectToFollow = true;
            speedInPixel = 3.5;
            targetCoordinates = followMe.getPosition().clone();
            double distance = position.distance(targetCoordinates);
            if (followMe.getClass() == Chopper.class) {
                targetCoordinates.y += 50;
                targetCoordinates.x += 20;
            } else {
                targetCoordinates.y += 40;
                targetCoordinates.x += 6;
            }

            if (distance >= speedInPixel) {
                position.right((targetCoordinates.x - position.x) / distance * speedInPixel);
                position.down((targetCoordinates.y - position.y) / distance * speedInPixel);
            }
        } else if (state == State.SIT) {

        }

    }

}



