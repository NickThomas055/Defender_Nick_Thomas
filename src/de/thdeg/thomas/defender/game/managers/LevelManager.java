package de.thdeg.thomas.defender.game.managers;

import de.thdeg.thomas.defender.graphics.level.Level;

public class LevelManager {
    Level level1;

    public LevelManager(boolean difficultyIsSetToEasy) {

        if (difficultyIsSetToEasy) {
            level1.setDifficulty("Easy");

        } else {
            level1.setDifficulty("Normal");
        }
    }

    public boolean hasNextLevel() {
        return true;
    }
    public boolean getNextLevel(){
        return true;
    }
}

