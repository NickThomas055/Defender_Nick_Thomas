package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;

import java.awt.*;
import java.util.ArrayList;

/**
 * creates a shot object that damages alien objects
 */
public class Shot extends Projectile implements MovingGameObject {
    final private int damage;
    private boolean flyFromLeftToRight;
    private Chopper.Direction direction;


    /**
     * Generates a basic shot object, Position needs to be changed separately
     *
     * @param gameView             responsible for visual display
     * @param collidableGameObject list of all the objects that the shot could collide with
     */
    public Shot(GameView gameView, ArrayList<CollidableGameObject> collidableGameObject,Chopper.Direction direction) {
        super(gameView, collidableGameObject);
        hitBox.width = 10;
        hitBox.height = 5;
        this.damage = 100;
        this.direction = direction;
        this.speedInPixel = 2;
        this.position = new Position(200, 200);
        this.width = (int) (24 * size);
        this.height = (int) (9 * size);
        this.size = 1;
        this.rotation = 0;
        flyFromLeftToRight = true;
        int number = gameView.playSound("shot.wav", false);
    }

    /**
     * draws rocket on canvas
     */
    @Override
    public void addToCanvas() {
        gameView.addLineToCanvas(position.x, position.y + 15, position.x + 10, position.y + 15, 3, Color.white);

    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        gamePlayManager.destroy(this);
        if (otherObject.getClass() == Alien.class) {
            gamePlayManager.destroyAliens(otherObject);
            int number = gameView.playSound("dead.wav", false);
        } else if (otherObject.getClass() == Ship.class) {
            gamePlayManager.destroyShips(otherObject);
            int number = gameView.playSound("shipDeath.wav", false);
        }
        System.out.println("hit!");


    }

    /**
     * @param position sets position of object
     */
    public void setPosition(Position position) {
        this.position = position;
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
     * steps 1 position in x-coordinates
     */
    @Override
    public void updatePosition() {
        if(direction== Chopper.Direction.RIGHT){
        this.position.x += 10.5;
        }else{
            this.position.x -= 10.5;
        }

    }

    /**
     * @return returns size of Shot object
     */
    public double getSize() {
        return (this.size);
    }

    /**
     * @return returns speed of object
     */
    public double getSpeedInPixel() {
        return (this.speedInPixel);
    }

    /**
     * @param Size sets size of object
     */
    public void setSize(double Size) {
        this.size = Size;
    }


    /**
     * @param height sets height of object
     */
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void updateStatus() {
        if (position.x >= GameView.WIDTH || position.x <= 0) {
            gamePlayManager.destroy(this);
        }

    }
}
