package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;

import java.util.ArrayList;

/**
 * creates a shot object that damages alien objects
 */
public class Shot extends Projectile implements MovingGameObject{
    final private int explosionRadius;
    final private int damage;
    final private String color;
    private String explosionColor;
    private boolean flyFromLeftToRight;


    /**
     * Generates a basic shot object, Position needs to be changed seperately
     * @param gameView responsible for visual dislay
     * @param collidableGameObject list of all the objects that the shot could collide with
     */
    public Shot(GameView gameView, ArrayList<CollidableGameObject> collidableGameObject) {
        super(gameView,collidableGameObject);
        hitBox.width = 30;
        hitBox.height = 10;
        this.damage = 100;
        this.speedInPixel = 2;
        this.position = new Position(200, 200);
        this.width = (int) (24 * size);
        this.height = (int) (9 * size);
        this.size = 1;
        this.rotation = 0;
        this.color = "Red";
        this.explosionRadius = 10;
        flyFromLeftToRight = true;
    }
    /**
     * draws rocket on canvas
     */
    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("Rocket.png", position.x, position.y, size, rotation);
      //  gameView.addRectangleToCanvas(hitBox.x,hitBox.y,hitBox.width, hitBox.height,2,false, Color.red);
    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        gamePlayManager.destroy(this);
        if (otherObject.getClass()==Alien.class){
            gamePlayManager.destroyAliens(otherObject);
        } else if(otherObject.getClass()==Ship.class) {
            gamePlayManager.destroyShips(otherObject);
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
     * steps 1 position in x-coordiantes
     */
    @Override
    public void updatePosition() {
        this.position.x += 3.5;
    }

    /**
     * deletes the object
     */
    public void delete() {
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

    private int getExplosionRadius() {
        return (explosionRadius);
    }

    private double calculateSpeed() {
        return (speedInPixel * 2);
    }

    /**
     * @param height sets height of object
     */
    public void setHeight(int height) {
        this.height = height;
    }

    private void calculateVelocitiy() {
    }


    @Override
    public void updateStatus(){
        if(position.x >= GameView.WIDTH || position.x <= 0){
            gamePlayManager.destroy(this);
        }

    }
}
