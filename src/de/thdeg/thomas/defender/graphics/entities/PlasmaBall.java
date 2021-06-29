package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;

import java.util.ArrayList;

/**
 * class for a projectile that gets spawned by AI
 */
public class PlasmaBall extends Projectile implements MovingGameObject {
    final private int damage;


    /**
     * Generates a basic shot object, Position needs to be changed separately
     *
     * @param gameView             responsible for visual display
     * @param collidableGameObject list of all the objects that the shot could collide with
     */
    public PlasmaBall(GameView gameView, ArrayList<CollidableGameObject> collidableGameObject) {
        super(gameView, collidableGameObject);
        hitBox.width = 30;
        hitBox.height = 10;
        this.damage = 25;
        this.speedInPixel = 2;
        this.position = new Position(200, 200);
        this.width = (int) (24 * size);
        this.height = (int) (9 * size);
        this.size = 1;
        this.rotation = 0;
        int number = gameView.playSound("shipShot.wav", false);

    }

    /**
     * draws rocket on canvas
     */
    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("plasmaball.png", position.x, position.y, size, rotation);
        //  gameView.addRectangleToCanvas(hitBox.x,hitBox.y,hitBox.width, hitBox.height,2,false, Color.red);
    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        gamePlayManager.hitPlayer(damage);
        gamePlayManager.destroy(this);


        System.out.println("hit!");


    }


    /**
     * @param position sets position of object
     */
    public void setPosition(Position position) {
        this.position = position;
    }


    /**
     * steps 1 position in x-coordinates
     */
    @Override
    public void updatePosition() {
        this.position.x -= 3.5;
    }

    @Override
    public void updatePosition(double o) {
        this.position.x -= 3.5;
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


