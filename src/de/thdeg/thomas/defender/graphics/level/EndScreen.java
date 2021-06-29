package de.thdeg.thomas.defender.graphics.level;

import de.thdeg.thomas.defender.gameview.GameView;

public class EndScreen {
    GameView gameView;
    public EndScreen(GameView gameView){
        this.gameView = gameView;
    }
    public void showEndScreen(String message){
        gameView.showEndScreen(message);
    }
}
