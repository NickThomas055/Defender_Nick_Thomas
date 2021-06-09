package de.thdeg.thomas.defender.graphics.level;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;
import de.thdeg.thomas.defender.graphics.entities.GameObject;


/**
 * parent class for all background Objects that don't interact with anything
 */
abstract public class Background extends GameObject {
    String color;

    Background(GameView gameView) {
        super(gameView);
    }

    /**
     * @return returns current size of hills object
     */
    public double getSize() {
        return (this.size);
    }

    /**
     * @return returns speed of Hills
     * speed of hills changes with speed of player
     */
    public double getSpeedInPixel() {
        return (this.speedInPixel);
    }

    /**
     * @param position changes position of background hills
     */
    public void setPosition(Position position) {
        this.position = position;
    }

}
