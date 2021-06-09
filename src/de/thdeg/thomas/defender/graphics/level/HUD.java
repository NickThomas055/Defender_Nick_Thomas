package de.thdeg.thomas.defender.graphics.level;

import de.thdeg.thomas.defender.gameview.GameView;
import de.thdeg.thomas.defender.graphics.level.Background;

import java.awt.*;

public class HUD extends Background {

    public HUD(GameView gameView){
        super(gameView);

    }
    @Override
    protected void updateStatus() {

    }

    @Override
    public void addToCanvas() {
        gameView.addLineToCanvas(0,GameView.HEIGHT-80,GameView.WIDTH,GameView.HEIGHT-80,10, Color.red);


    }
}
