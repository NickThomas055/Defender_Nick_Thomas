package de.thdeg.thomas.defender.graphics.level;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;
import de.thdeg.thomas.defender.graphics.entities.MovingGameObject;

/**
 * class for displaying the ground of the level
 */
public class Ground extends Background implements MovingGameObject {


    /**
     * creates clouds in the background with preset parameters
     * can be modified with {@link #setPosition(Position)}
     */
    public Ground(GameView gameView) {
        super(gameView);
        this.gameView = gameView;
        this.color = "white";
        this.size = 100;
        this.rotation = 0;
        this.speedInPixel = 0;
        this.width = 400;
        this.height = 200;

    }
    @Override
    /**
     * draws hills on the background
     */
    public void addToCanvas() {

        gameView.addImageToCanvas("ground.png",position.x,position.y+150,5,0);
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

    @Override
    /** Moves the object slightly to the right.
     *
     * @param speedInPixel sets the amount of acceleration, the object gets
     */
    public void updatePosition(double speedInPixel) {
        this.position.x += 1 * speedInPixel;

    }


    @Override
    public void adaptPosition(double adaptX, double adaptY) {
        position.x += adaptX*1.5;

    }
}

