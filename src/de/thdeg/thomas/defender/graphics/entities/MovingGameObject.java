package de.thdeg.thomas.defender.graphics.entities;

/**
 * interface for moving gameObjects
 */
public interface MovingGameObject {
    void updatePosition();

    void updatePosition(double SpeedInPixel);
}
