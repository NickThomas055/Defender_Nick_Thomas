package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;

import java.awt.*;
import java.util.Random;

/**
 * Ball that has the ability to follow another ball, hide or move to the middle
 */
public class FollowerBall extends AIControlled implements MovingGameObject {

    private enum State {
        CENTER, FOLLOW, HIDE
    }

    private Position targetCoordinates;
    private State state;
    private boolean hide;
    Random random;
    RandomBall followMe;


    /**
     * constructs a FollowerBall Object
     *
     * @param gameView
     * @param followMe object the ball has to follow
     */
    public FollowerBall(GameView gameView, RandomBall followMe) {
        super(gameView);
        this.followMe = followMe;
        random = new Random();
        this.health = 200;
        this.state = State.CENTER;
        this.hide = false;
        hitBox.width = 60;
        hitBox.height = 35;
        this.speedInPixel = 3.5;
        this.position = new Position(GameView.WIDTH, GameView.HEIGHT);
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

    @Override
    /**
     * draws the Alien on the canvas
     */
    public void addToCanvas() {
        if (hide) {
            gameView.addOvalToCanvas(position.x, position.y, size, size, 5, true, Color.white);
        } else {
            gameView.addOvalToCanvas(position.x, position.y, size, size, 5, true, Color.green);
        }
    }

    @Override
    /**
     * Moves the object slightly to the right.
     *
     * @param speedInPixel sets the amount of acceleration, the object gets
     */
    public void updatePosition(double speedInPixel) {


    }

    @Override
    public void updatePosition() {
        if (gameView.timerExpired("balltimer", "FollowerBall")) {
            gameView.setTimer("balltimer", "FollowerBall", 3000);
            System.out.println(state);
            switch (state) {
                case CENTER -> state = State.FOLLOW;
                case FOLLOW -> state = State.HIDE;
                case HIDE -> state = State.CENTER;
            }
        }
        if (state == State.CENTER) {
            hide = false;
            targetCoordinates = new Position(GameView.WIDTH / 2d, GameView.HEIGHT / 2d);
            double distance = position.distance(targetCoordinates);
            if (distance >= speedInPixel) {
                position.right((targetCoordinates.x - position.x) / distance * speedInPixel);
                position.down((targetCoordinates.y - position.y) / distance * speedInPixel);
            }
        } else if (state == State.FOLLOW) {
            hide = false;
            targetCoordinates = followMe.getPosition().clone();
            double distance = position.distance(targetCoordinates);
            targetCoordinates.y += 25;
            if (distance >= speedInPixel) {
                position.right((targetCoordinates.x - position.x) / distance * speedInPixel);
                position.down((targetCoordinates.y - position.y) / distance * speedInPixel);
            }
        } else if (state == State.HIDE) {
            hide = true;
        }
    }

}



