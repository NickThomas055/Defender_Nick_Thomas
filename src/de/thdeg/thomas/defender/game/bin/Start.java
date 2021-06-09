package de.thdeg.thomas.defender.game.bin;

import de.thdeg.thomas.defender.game.managers.GameLoopManager;

/**
 * main game class, manages initialization and spawning of entities
 */
public class Start {

    /**
     * main method for game process
     *
     * @param args
     */
    public static void main(String[] args) {
        GameLoopManager gameLoopManager = new GameLoopManager();
        gameLoopManager.startGame();

    }
}
