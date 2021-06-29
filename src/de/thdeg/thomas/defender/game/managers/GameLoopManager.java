package de.thdeg.thomas.defender.game.managers;

import de.thdeg.thomas.defender.gameview.GameView;

/**
 * manages display and game logic
 */
public class GameLoopManager {
    private final GameView gameView;
    private final InputManager inputManager;
    private final GameObjectManager gameObjectManager;
    private final GamePlayManager gamePlayManager;

    /**
     * sets basic parameters to display
     * also initializes block images
     */
    public GameLoopManager() {
        this.gameView = new GameView();
        this.gameView.setWindowTitle("Defender");
        this.gameView.setStatusText("Nick Thomas - Java Programmierung SS 2021");
        this.gameView.setWindowIcon("Player.png");
        this.gameObjectManager = new GameObjectManager(gameView);
        this.inputManager = new InputManager(gameView, gameObjectManager.getPlayer());
        this.gamePlayManager = new GamePlayManager(gameView, gameObjectManager);


    }

    /**
     * paints game objects
     */
    public void startGame() {
        while (true) {

            gamePlayManager.updateGamePlay();
            inputManager.updateUserInputs();
            gameObjectManager.updateGameObjects();
            gameView.printCanvas();
        }
    }

}
