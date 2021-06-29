package de.thdeg.thomas.defender.graphics.level;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;
import de.thdeg.thomas.defender.graphics.entities.MovingGameObject;

/**
 * creates a background object for visual purposes
 */
public class Hills extends Background implements MovingGameObject {


    /**
     * creates hills in the background with preset parameters
     * can be modified with {@link #setPosition(Position)}
     */
    public Hills(GameView gameView) {
        super(gameView);
        this.gameView = gameView;
        this.color = "white";
        this.size = 100;
        this.rotation = 0;
        this.speedInPixel = 0;
        this.width = 400;
        this.height = 200;

    }

    /**
     * draws hills on the background
     */
    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("hills.png", position.x, position.y, 5, 0);

    }

    /**
     * updates status of Object
     */
    @Override
    public void updateStatus() {

    }


    /**
     * moves the object slightly to the right, changes direction when on window border
     */
    @Override
    public void updatePosition() {
        this.position.x += 1;
    }

    /**
     * Moves the object slightly to the right.
     *
     * @param speedInPixel sets the amount of acceleration, the object gets
     */
    @Override
    public void updatePosition(double speedInPixel) {
        this.position.x += 1 * speedInPixel;

    }


    @Override
    public void adaptPosition(double adaptX, double adaptY) {
        position.x += adaptX;
    }
}
