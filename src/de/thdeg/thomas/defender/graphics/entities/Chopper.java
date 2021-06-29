package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.game.managers.GameObjectManager;
import de.thdeg.thomas.defender.gameview.GameView;


/**
 * player class
 */
public class Chopper extends Vehicles {
    private boolean shooting;

    private State state;
    private Direction direction;
    private int sound;


    /**
     * @param gameView implements graphics
     */
    public Chopper(GameView gameView) {
        super(gameView);
        this.health = 200;
        this.state = State.STANDARD;
        hitBox.width = 60;
        hitBox.height = 35;
        this.direction = Direction.RIGHT;
        this.speedInPixel = 2.0;
        this.position = new Position(200, 200);
        this.amountOfAmmo = 100;
        this.width = (int) (24 * size);
        this.height = (int) (9 * size);
        this.size = 2;
        this.rotation = 0;
        sound = gameView.playSound("idle.wav", true);

    }

    private enum State {
        STANDARD, DAMAGED, EXPLODING, EXPLODED

    }

    public enum Direction {
        RIGHT, LEFT
    }


    /**
     * moves player to the left
     */
    public void left() {
        if (gameView.timerExpired("soundtimer", "soundtimer")) {
            sound = gameView.playSound("boost.wav", false);
            gameView.setTimer("soundtimer", "soundtimer", 600);
        }
        if (position.x < 100) {
            gamePlayManager.chopperMovingLeft();

        } else {
            position.left(speedInPixel);
        }
        direction = Direction.LEFT;
    }


    /**
     * moves player to the right
     */
    public void right() {
        if (gameView.timerExpired("soundtimer", "soundtimer")) {
            sound = gameView.playSound("boost.wav", false);
            gameView.setTimer("soundtimer", "soundtimer", 600);
        }
        if (position.x > 800) {
            gamePlayManager.chopperMovingRight();
        } else {

            position.right(speedInPixel);
        }
        direction = Direction.RIGHT;
    }


    /**
     * moves player up
     */
    public void up() {
        if (gameView.timerExpired("soundtimer", "soundtimer")) {
            sound = gameView.playSound("boost.wav", false);
            gameView.setTimer("soundtimer", "soundtimer", 600);
        }
        if (position.y >= 1) {
            position.up(speedInPixel);

        }
    }

    /**
     * moves player down
     */
    public void down() {
        if (gameView.timerExpired("soundtimer", "soundtimer")) {
            sound = gameView.playSound("boost.wav", false);
            gameView.setTimer("soundtimer", "soundtimer", 600);
        }
        if (position.y <= GameView.HEIGHT - 160)
            position.down(speedInPixel);
    }

    /**
     * updates status of Object
     */
    @Override
    public void updateStatus() {
        this.health = gamePlayManager.player.getPlayerHealth();
        explodeChopper();


    }


    /**
     * fires player weapon
     */
    public void shoot() {
        if (gameView.timerExpired("shottimer", "shottimer")) {
            gamePlayManager.shootRocketChopper(position, direction);
            gameView.setTimer("shottimer", "shottimer", 300);
        }
    }

    private void explodeChopper() {
        if (health <= 0) {
            state = State.EXPLODED;
        }
    }

    /**
     * resets chopper position when a new game is created
     */
    public void resetPosition(){
        this.position = new Position(200, 200);
    }

    /**
     * draws the Alien on the canvas
     */
    @Override
    public void addToCanvas() {
        if (direction == Direction.RIGHT) {
            if (state == State.EXPLODED) {
                gameView.addImageToCanvas("Herz.png", position.x, position.y, size, rotation);
                state= State.STANDARD;
                gamePlayManager.gameOver = true;

            } else {

                gameView.addImageToCanvas("Player.png", position.x, position.y, size, rotation);
                // gameView.addRectangleToCanvas(hitBox.x,hitBox.y,hitBox.width, hitBox.height,2,false,Color.red);
            }
        } else if (direction == Direction.LEFT) {
            if (state == State.EXPLODED) {
                gameView.addImageToCanvas("Herz.png", position.x, position.y, size, rotation);

            } else {
                gameView.addImageToCanvas("FlipPlayer.png", position.x, position.y, size, rotation);
                // gameView.addRectangleToCanvas(hitBox.x,hitBox.y,hitBox.width, hitBox.height,2,false,Color.red);
            }

        }

    }

}
