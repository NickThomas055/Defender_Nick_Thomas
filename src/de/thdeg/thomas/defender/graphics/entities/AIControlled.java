package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.gameview.GameView;

/**
 * abstract class for AI enemies and vehicles
 */
abstract public class AIControlled extends Vehicles {
    AIControlled(GameView gameView) {
        super(gameView);
    }
}
