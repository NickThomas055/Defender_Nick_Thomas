package de.thdeg.thomas.defender.graphics.level;

import de.thdeg.thomas.defender.gameview.GameView;

import java.awt.*;

/**
 * manages display of the HUD
 */
public class HUD extends Background {
    private int gameLevel;

    /**
     * constructs a HUD
     *
     * @param gameView responsible for visuals
     */
    public HUD(GameView gameView) {
        super(gameView);
        gameLevel = 1;


    }

    @Override
    public void update() {

    }

    @Override
    protected void updateStatus() {
        gameLevel = gamePlayManager.getWaveLevel();


    }

    /**
     * updates the value for wave display
     *
     * @param wave new wave integer
     */
    public void updateGameLevel(int wave) {
        gameLevel = wave;
    }


    @Override
    public void addToCanvas() {
        gameView.addLineToCanvas(0, GameView.HEIGHT - 75, GameView.WIDTH, GameView.HEIGHT - 75, 10, Color.CYAN);
        for(int i = 0; i < Player.playerHealth/25;i++){
            gameView.addImageToCanvas("Player.png", 20+40*i, GameView.HEIGHT - 40, 1, 0);
        }
        gameView.addImageToCanvas("Rocket.png", 200, GameView.HEIGHT - 60, 1, 0);
        gameView.addImageToCanvas("Rocket.png", 200, GameView.HEIGHT - 50, 1, 0);
        gameView.addImageToCanvas("Rocket.png", 200, GameView.HEIGHT - 40, 1, 0);
        gameView.addImageToCanvas("Rocket.png", 200, GameView.HEIGHT - 30, 1, 0);
        gameView.addTextToCanvas("Wave : " + gameLevel, 10, GameView.HEIGHT - 65, 20, Color.yellow, 0);
        if (gameView.timerExpired("blinktimer", "blinktimer")) {
            gameView.addTextToCanvas("Score:"+ Player.score,685, GameView.HEIGHT - 50, 25, Color.CYAN, 0);
            gameView.setTimer("blinktimer", "blinktimer", 100);
        } else {
            gameView.addTextToCanvas("Score:"+ Player.score,685, GameView.HEIGHT - 50, 25, Color.BLUE, 0);

        }


    }
}
