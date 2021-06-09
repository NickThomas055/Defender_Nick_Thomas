package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.gameview.GameView;
import java.awt.*;
import java.util.Objects;

/** Represents all game objects that are able to collide with something. */
public abstract class CollidableGameObject extends GameObject implements Cloneable{
    protected Rectangle hitBox;

    protected CollidableGameObject(GameView gameView) {
        super(gameView);
        this.hitBox = new Rectangle((int)this.position.x, (int)this.position.y, 50, 50);
    }

    @Override
    public void update() {
        super.update();
        updateHitBoxPosition();
    }

    /**
     * Used to update the hitBox position of the game object.
     */
    protected abstract void updateHitBoxPosition();

    /**
     * If a GameObject has collided with something, patternIterator is able to react to the collision.
     *
     * @param otherObject The other GameObject that is involved in the collision.
     */
    public abstract void reactToCollision(CollidableGameObject otherObject);

    @Override
    public void adaptPosition(double adaptX, double adaptY) {
        super.adaptPosition(adaptX, adaptY);
        updateHitBoxPosition();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollidableGameObject other = (CollidableGameObject) o;
        return  this.position.equals(other) && this.size == other.size;
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
    public CollidableGameObject clone() {
        CollidableGameObject other = null;
            other = (CollidableGameObject) super.clone();
        return other;
    }

}