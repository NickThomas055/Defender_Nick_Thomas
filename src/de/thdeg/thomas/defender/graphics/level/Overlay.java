package de.thdeg.thomas.defender.graphics.level;

import de.thdeg.thomas.defender.gameview.GameView;
import de.thdeg.thomas.defender.graphics.entities.GameObject;

import java.awt.*;

public class Overlay extends GameObject {
    private boolean isTimeToShow;
    private String message;

    public Overlay(GameView gameView) {
        super(gameView);
        this.isTimeToShow = false;
        this.message = "#EMPTY#";

    }

    public void ShowMessage(String message) {
        this.message = message;
        this.isTimeToShow = true;
        gameView.setTimer("messagetimer", "messagetimer", 1);

    }

    @Override
    protected void updateStatus() {


    }

    @Override
    public void addToCanvas() {
        System.out.print(this.isTimeToShow == true);
        if (this.isTimeToShow) {

            gameView.addRectangleToCanvas(0,0,GameView.WIDTH,gameView.HEIGHT,1,true,Color.black);
            gameView.addTextToCanvas(message, GameView.WIDTH / 2d-300, GameView.HEIGHT / 2d, 40, Color.white, 0);

        }
        if (gameView.timerExpired("messagetimer", "messagetimer")) {
        isTimeToShow = false;

         }


    }
}
