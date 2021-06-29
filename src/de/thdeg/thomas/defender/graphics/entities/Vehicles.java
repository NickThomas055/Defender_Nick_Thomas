package de.thdeg.thomas.defender.graphics.entities;


import de.thdeg.thomas.defender.gameview.GameView;

/**
 * parent class for all kinds of vehicles
 */
abstract public class Vehicles extends CollidableGameObject {
    int amountOfAmmo;
    int health;
    boolean flyFromLeftToRight;


    Vehicles(GameView gameView) {
        super(gameView);
    }

    /**
     * @return returns size of object
     */
    public double getSize() {
        return (this.size);
    }

    /**
     * responsible for aligning a hitbox with an object
     */
    @Override
    public void updateHitBoxPosition() {
        this.hitBox.x = (int) this.position.x;
        this.hitBox.y = (int) this.position.y + 10;

    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {


    }

    /**
     * @return returns speed of Object
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
     * @param height height of object
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
