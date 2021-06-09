package de.thdeg.thomas.defender.game.managers;

import de.thdeg.thomas.defender.gameview.GameView;
import de.thdeg.thomas.defender.graphics.entities.*;
import de.thdeg.thomas.defender.graphics.level.Clouds;
import de.thdeg.thomas.defender.graphics.level.Ground;
import de.thdeg.thomas.defender.graphics.level.HUD;
import de.thdeg.thomas.defender.graphics.level.Hills;

import java.util.LinkedList;

/**
 * manages all visible GameObjects
 */
public class GameObjectManager {

    private GameView gameView;
    private String house;
    /**
     * private final Alien alien;
     * private final Hills hills;
     * private final Shot rocket;
     * private final Ship ship;
     */
    private final Chopper chopper;
    private final Hills hills;
    private final Clouds clouds;
    private final Ground ground;
    private final RandomBall ball;
    private final HUD hud;
    private final FollowerBall followerBall;
    private static final boolean NODIAGONAL = true;
    LinkedList<GameObject> gameObjects;
    LinkedList<Alien> aliens;
    LinkedList<Laser> lasers;
    LinkedList<PowerUp> powerups;
    LinkedList<Ship> ships;
    LinkedList<Shot> shots;
    LinkedList<Vehicles> vehicles;


    /**
     * initializes all visible GameObjects
     *
     * @param gameView is responsible for visuals
     */
    public GameObjectManager(GameView gameView) {
        this.gameView = gameView;
        this.hills = new Hills(gameView);
        this.clouds = new Clouds(gameView);
        this.ground = new Ground(gameView);
        this.chopper = new Chopper(gameView);
        this.ball = new RandomBall(gameView);
        this.followerBall = new FollowerBall(gameView, ball);
        this.gameObjects = new LinkedList<>();
        this.aliens = new LinkedList<>();
        this.lasers = new LinkedList<>();
        this.vehicles = new LinkedList<>();
        this.aliens = new LinkedList<>();
        this.shots = new LinkedList<>();
        this.ships = new LinkedList<>();
        this.hud = new HUD(gameView);

    }

    /**
     * sets new position of GameObjects and draws them on the screen
     */
    public void updateGameObjects() {
        gameObjects.clear();

        gameObjects.add(clouds);
        gameObjects.add(hills);
        gameObjects.add(ground);
        gameObjects.add(hud);
        gameObjects.addAll(aliens);
        gameObjects.add(chopper);
        gameObjects.addAll(shots);
        gameObjects.addAll(ships);
        gameObjects.add(ball);
        gameObjects.add(followerBall);



        for (var gameObject : gameObjects) {
            if (gameObject.getClass() == hills.getClass() || gameObject.getClass() == clouds.getClass() || gameObject.getClass() == ground.getClass() || gameObject.getClass() == hud.getClass()) {
                gameObject.addToCanvas();
            } else {
                gameObject.update();
                gameObject.addToCanvas();
            }
        }
    }

    /**
     * moves the Background when the chopper is far right or left enough
     *
     * @param adaptX
     * @param adaptY
     */
    public void moveWorld(double adaptX, double adaptY) {
        for (var gameObject : gameObjects) {
            if (gameObject.getClass() == chopper.getClass()) {
                continue;
            } else {
                gameObject.adaptPosition(adaptX, adaptY);
            }
        }

    }

    /**
     * returns playable object
     *
     * @return the player character
     */
    Chopper getPlayer() {
        return (chopper);
    }

    /**
     * returns list of all GameObjects
     *
     * @return list of all gameobjects
     */
    public LinkedList<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * returns list of all Ships visible
     *
     * @return list of all ships
     */
    public LinkedList getShips() {
        return ships;
    }

    /**
     * returns list of all active shot objects
     *
     * @return list of all shots
     */
    public LinkedList getShots() {
        return shots;
    }

    /**
     * returns all shown powerups
     *
     * @return list of all powerups
     */
    public LinkedList getPowerUp() {
        return powerups;
    }

    /**
     * returns list of used Laser (should only be one)
     *
     * @return list of all lasers
     */
    public LinkedList getLaser() {
        return lasers;
    }

    /**
     * returns list of all alien objects
     *
     * @return list of all alien objects
     */
    public LinkedList getAliens() {
        return aliens;
    }
}
