package de.thdeg.thomas.defender.game.managers;

import de.thdeg.thomas.defender.gameview.GameView;
import de.thdeg.thomas.defender.graphics.entities.Chopper;

import java.awt.event.KeyEvent;

/**
 * manages all user inputs
 */
public class InputManager {
    private final GameView gameView;
    private final Chopper chopper;
    private static final boolean NODIAGONAL = false;

    /**
     * constructor for InputManager
     *
     * @param gameView responsible for graphics
     * @param chopper  responsible for player movement
     */
    public InputManager(GameView gameView, Chopper chopper) {
        this.gameView = gameView;
        this.chopper = chopper;
    }

    /**
     * changes position of Player based on the button being pressed
     */
    void updateUserInputs() {
        Integer[] pressedKeys = gameView.getKeyCodesOfCurrentlyPressedKeys();
        for (int keyCode : pressedKeys) {

            //if (pressedKeys.length == 1) {
            if (keyCode == KeyEvent.VK_LEFT) {
                chopper.left();
                if (NODIAGONAL) {
                    break;
                }
            } else if (keyCode == KeyEvent.VK_DOWN) {
                chopper.down();
                if (NODIAGONAL) {
                    break;
                }

            } else if (keyCode == KeyEvent.VK_RIGHT) {
                chopper.right();
                if (NODIAGONAL) {
                    break;
                }

            } else if (keyCode == KeyEvent.VK_UP) {
                chopper.up();
                if (NODIAGONAL) {
                    break;
                }

            } else if (keyCode == KeyEvent.VK_SPACE) {
                chopper.shoot();
                if (NODIAGONAL) {
                    break;
                }

            }
        }
    }

}
