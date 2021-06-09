package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;

import java.util.Objects;
import java.util.Random;

/**
 * parent class for different kinds of PowerUp's
 */
public class PowerUp extends CollidableGameObject implements MovingGameObject,Cloneable{
    Random random;
    effects effect;
    private final String objectID;

    PowerUp(GameView gameView) {
        super(gameView);
        random = new Random();
        hitBox.width = 60;
        hitBox.height = 40;
        this.speedInPixel = 0;
        this.effect = effects.values()[new Random().nextInt(effects.values().length)];
        this.position = new Position(random.nextInt(GameView.WIDTH - 0) + 0, random.nextInt(GameView.HEIGHT - 0) + 0);
        this.width = (int) (24 * size);
        this.height = (int) (9 * size);
        this.size = 2;
        this.rotation = 0;
        this.objectID = "PowerUp" + (int) position.x + (int) position.y;

    }
    private enum effects{
        HEALTH,AMMO,LASER,BOMB
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
    @Override
    public void reactToCollision(CollidableGameObject otherObject) {


    }
    @Override
    public void updateHitBoxPosition(){
        this.hitBox.x = (int)this.position.x;
        this.hitBox.y = (int)this.position.y+10;

    }
    @Override
    public boolean equals(Object o) {
        if (this==o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PowerUp other = (PowerUp) o;
        return this.effect == other.effect && this.speedInPixel == other.speedInPixel && this.position.equals(other) && this.size == other.size;
    }

    /**
     * generates hashCode for object
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(position.x, position.y, effect, speedInPixel,size);

    }

    /**
     * clones object
     *
     * @return a clone
     */
    @Override
    public PowerUp clone() {
        PowerUp other = null;
            other = (PowerUp) super.clone();
            other.position = this.position.clone();


        return other;
    }
}
