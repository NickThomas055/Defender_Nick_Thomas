package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;

import java.awt.*;

/**
 * player class
 */
public class Chopper extends Vehicles {
    private boolean shooting;
    private static final boolean SHOWX = false;
    private Status status;

    /**
     * @param gameView implements graphics
     */
    public Chopper(GameView gameView) {
        super(gameView);
        this.health = 200;
        this.status = Status.STANDARD;
        hitBox.width = 60;
        hitBox.height = 35;
        this.speedInPixel = 2.0;
        this.position = new Position(200, 200);
        this.amountOfAmmo = 100;
        this.width = (int) (24 * size);
        this.height = (int) (9 * size);
        this.size = 2;
        this.rotation = 0;
    }

    /**
     * moves player to the left
     */
    public void left() {
        if (position.x <100) {
            gamePlayManager.chopperMovingLeft(speedInPixel);

        } else {
            position.left(speedInPixel);
        }
    }



    /**
     * moves player to the right
     */
    public void right() {
        if (position.x > 800) {
            gamePlayManager.chopperMovingRight(speedInPixel);
        } else {

            position.right(speedInPixel);
        }
    }


    /**
     * moves player up
     */
    public void up() {
        position.up(speedInPixel);
    }

    /**
     * moves player down
     */
    public void down() {
        position.down(speedInPixel);
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
    public void shoot() {
        if (gameView.timerExpired("shottimer", "shottimer")) {
            gamePlayManager.shootRocketChopper(position);
            gameView.setTimer("shottimer", "shottimer", 300);
        }

        if (SHOWX) {
            shooting = true;
        }
    }

    @Override
    /**
     * draws the Alien on the canvas
     */
    public void addToCanvas() {
        if (SHOWX) {
            if (!shooting) {
                gameView.addTextToCanvas("X", position.x, position.y, 50, Color.WHITE, 0);
            } else {
                gameView.addTextToCanvas("O", position.x, position.y, 50, Color.WHITE, 0);
                shooting = false;
            }
        } else {
            gameView.addImageToCanvas("Player.png", position.x, position.y, size, rotation);
           // gameView.addRectangleToCanvas(hitBox.x,hitBox.y,hitBox.width, hitBox.height,2,false,Color.red);
        }

    }
    private enum Status{
        STANDARD,DAMAGED,EXPLODING,EXPLODED
    }
}