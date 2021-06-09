package de.thdeg.thomas.defender.graphics.level;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;
import de.thdeg.thomas.defender.graphics.entities.MovingGameObject;

/**
 * creates a background object for visual purposes
 */
public class Hills extends Background implements MovingGameObject {
    double[] coordinatesx;
    double[] coordinatesy;


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
    @Override
    /**
     * draws hills on the background
     */
    public void addToCanvas() {
        //this.coordinatesx = new double[]{position.x + 0, position.x + 50, position.x + 150, position.x + 250, position.x + 350, position.x + 450, position.x + 550, position.x + 650, position.x + 750, position.x + 850, position.x + 950};
       // this.coordinatesy = new double[]{position.y + 50, position.y + 50, position.y + 100, position.y + 150, position.y + 100, position.y + 200, position.y + 250, position.y + 100, position.y + 50, position.y + 100, position.y + 150};
       // gameView.addPolyLineToCanvas(coordinatesx,coordinatesy,7, Color.WHITE);
        gameView.addImageToCanvas("hills.png",position.x,position.y,5,0);
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
        position.x += adaptX;
    }
}
