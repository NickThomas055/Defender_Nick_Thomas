package de.thdeg.thomas.defender.graphics.level;

/**
 * class responsible for keeping track of player values
 */
public class Player {
    final static int MAXHEALTH = 100;
    /**
     * amount of health the player has remaining
     */
    public static int playerHealth;
    /**
     * overall game score
     */
    public static int score;
    /**
     * for setting easy difficulty
     */
    public boolean difficultyIsSetToEasy;
    /**
     * level selector
     */
    public String level;

    /**
     * initializes player with basic parameters
     */
    public Player() {
        this.playerHealth = MAXHEALTH;
        this.score = 0;
        this.difficultyIsSetToEasy = true;
    }

    /**
     * @param score sets new score for the player
     */
    public void increaseScoreBy(int score) {
        this.score += score;
        System.out.print(this.score);
    }

    /**
     * subtracts player health by damageDone
     */
    public void damagePlayerHealth(int damageDone) {
        this.playerHealth -= damageDone;
    }

    /**
     * @return player health
     */
    public int getPlayerHealth() {
        return playerHealth;
    }


}
