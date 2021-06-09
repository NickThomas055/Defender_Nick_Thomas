package de.thdeg.thomas.defender.graphics.entities;


import de.thdeg.thomas.defender.gameview.GameView;

/**
 * class for special laser weapon
 */
public class Laser extends CollidableGameObject implements MovingGameObject{


    /**
     * constructor for Laser weapon
     *
     * @param gameView impliments graphics
     */
    public Laser(GameView gameView) {
        super(gameView);

    }
    @Override
    public void reactToCollision(CollidableGameObject otherObject) {

    }

    /**
     * responsible for aligning a hitbox with an object
     */
    @Override
    public void updateHitBoxPosition(){
        this.hitBox.x = (int)this.position.x;
        this.hitBox.y = (int)this.position.y+10;

    }
    /**
     * updates status of Object
     */
    @Override
    public void updateStatus() {

    }

    /**
     * updates Position of Object
     */
    @Override
    public void updatePosition() {

    }

    /**
     * updates Position of Object with speed parameter
     *
     * @param SpeedInPixel
     */
    @Override
    public void updatePosition(double SpeedInPixel) {

    }

    /**
     * draws Object on canvas
     */
    @Override
    public void addToCanvas() {

    }
}
