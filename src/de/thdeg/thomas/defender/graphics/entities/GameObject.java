package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;
import de.thdeg.thomas.defender.game.managers.GamePlayManager;

import java.util.Objects;

/**
 * parent class of all game objects
 */
public abstract class GameObject implements Cloneable{
    protected GameView gameView;
    protected double speedInPixel;
    protected Position position;
    protected double size;
    protected double rotation;
    protected int width;
    protected int height;
    protected GamePlayManager gamePlayManager;

    /**
     * constructs a GameObject
     * @param gameView
     */
    public GameObject(GameView gameView) {
        this.gameView = gameView;
        this.position = new Position(0, 0);
    }

    /**
     * updates position and status of gameObject
     */
    public void update() {
        if (this instanceof MovingGameObject) {
            ((MovingGameObject) this).updatePosition();
        }
        this.updateStatus();
    }

    protected abstract void updateStatus();

    /**
     * draws object on canvas
     */
    public abstract void addToCanvas();

    /**
     * getter method for object
     *
     * @return returns {@link Position} of object
     * @see Position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * responsible for passing GameObjects to the gamePlayManager
     *
     * @param gamePlayManager
     * @link GamePlayManager
     */
    public void setGamePlayManager(GamePlayManager gamePlayManager) {
        this.gamePlayManager = gamePlayManager;
    }


    /**
     * changes position by chosen amount
     * @param adaptX changes position.x
     * @param adaptY changes position.y
     */
    public void adaptPosition(double adaptX, double adaptY) {
        position.x += adaptX;
        position.y += adaptY;
    }
    @Override
    public String toString(){
        return this.getClass().getSimpleName()+position.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameObject other = (GameObject) o;
        return  this.position == other.position && this.size == other.size;
    }

    /**
     * generates hashCode for object
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(position.x, position.y,size);

    }
    @Override
    public GameObject clone() {
       GameObject other = null;
        try {
            other = (GameObject) super.clone();
        } catch (CloneNotSupportedException ignored) {
        }
        return other;
    }
}
