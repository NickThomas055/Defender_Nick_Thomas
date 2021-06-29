package de.thdeg.thomas.defender.graphics.level;

import de.thdeg.thomas.defender.gameview.GameView;

public class StartScreen {
    GameView gameView;
    public StartScreen(GameView gameView){
        this.gameView = gameView;
    }

    public void startScreen() {
        String title = "DEFENDER"; // Mehrzeilige Titel sind möglich (z.B. ASCII-ART).
        String description = "Aliens want to abduct our working class! \nYou are the only one who can save them! \nUse our last Chopper to fight them!"; // Text muss manuell formatiert werden.
        boolean difficultyIsSetToEasy = gameView.showSimpleStartScreen(title, description);
    }
}