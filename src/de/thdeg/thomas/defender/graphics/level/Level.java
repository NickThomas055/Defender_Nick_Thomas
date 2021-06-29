package de.thdeg.thomas.defender.graphics.level;

/**
 * class for keeping track of basic level parameters
 */
public class Level {
    /**
     * names the current level
     */
    public String levelName;
    /**
     * selects difficulty
     */
    public String difficulty;
    /**
     * counts the number of spawn waves survived
     */
    public int wave;

    /**
     * creates a new level with basic settings
     */
    public Level() {
        this.wave = 1;
        this.levelName = "Wave";
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return difficulty of the level
     */
    public String getDifficulty() {
        return this.difficulty;
    }

    /**
     * @return integer of the current number of the wave
     */
    public int getWave() {
        return wave;
    }

    /**
     * sets new waveLevel
     */
    public void nextWave() {
        this.wave += 1;
    }

    /**
     *
     * @param wave used to set wave back to 0
     */
    public void setWave(int wave) {
        this.wave = wave;
    }
}
