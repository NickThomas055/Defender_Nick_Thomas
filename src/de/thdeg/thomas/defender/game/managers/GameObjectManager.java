package de.thdeg.thomas.defender.game.managers;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;
import de.thdeg.thomas.defender.graphics.entities.*;
import de.thdeg.thomas.defender.graphics.level.*;

import java.util.LinkedList;

/**
 * manages all visible GameObjects
 */
public class GameObjectManager {

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
    private final Map map;
    private final Overlay overlay;
    LinkedList<GameObject> gameObjects;
    LinkedList<Alien> aliens;
    LinkedList<Laser> lasers;
    LinkedList<PowerUp> powerups;
    LinkedList<Ship> ships;
    LinkedList<Shot> shots;
    LinkedList<Vehicles> vehicles;
    LinkedList<PlasmaBall> plasmaBalls;
    LinkedList<Farmer> farmers;


    /**
     * initializes all visible GameObjects
     *
     * @param gameView is responsible for visuals
     */
    public GameObjectManager(GameView gameView) {
        this.map = new Map(gameView, this);
        this.hills = new Hills(gameView);
        this.clouds = new Clouds(gameView);
        this.ground = new Ground(gameView);
        this.chopper = new Chopper(gameView);
        this.ball = new RandomBall(gameView);
        this.gameObjects = new LinkedList<>();
        this.aliens = new LinkedList<>();
        this.lasers = new LinkedList<>();
        this.vehicles = new LinkedList<>();
        this.aliens = new LinkedList<>();
        this.shots = new LinkedList<>();
        this.ships = new LinkedList<>();
        this.hud = new HUD(gameView);
        this.plasmaBalls = new LinkedList<>();
        this.farmers = new LinkedList<>();
        this.overlay = new Overlay(gameView);


    }

    /**
     * sets new position of GameObjects and draws them on the screen
     */
    public void updateGameObjects() {
        gameObjects.clear();


        gameObjects.add(clouds);
        gameObjects.add(hills);
        gameObjects.add(ground);
        gameObjects.add(map);
        gameObjects.add(hud);
        gameObjects.add(chopper);
        gameObjects.addAll(shots);
        gameObjects.addAll(farmers);
        gameObjects.addAll(aliens);
        gameObjects.addAll(ships);
        gameObjects.add(ball);
        gameObjects.addAll(plasmaBalls);
        gameObjects.add(overlay);


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
     * @param adaptX adapts x-position
     * @param adaptY adapts y-position
     */
    public void moveWorld(double adaptX, double adaptY) {

        for (var gameObject : gameObjects) {
            if (gameObject.getClass() == chopper.getClass()) {

            } else {
                gameObject.adaptPosition(adaptX, adaptY);
            }
        }

    }

    /**
     * @return position of background clouds
     */
    public Position getCloudPosition() {
        return clouds.getPosition();
    }

    /**
     * @return list of all farmer entities
     */
    public LinkedList<Farmer> getFarmers() {
        return farmers;
    }

    /**
     * returns playable object
     *
     * @return the player character
     */
    public Chopper getPlayer() {
        return (chopper);
    }

    /**
     *
     * @return Overlay
     *
     */
    public Overlay getOverlay() {
        return overlay;
    }

    /**
     * updates HuD elements
     *
     * @param score sets new player score
     */
    public void updateHUD(int score) {
        hud.updateGameLevel(score);
    }

    /**
     * @return list of all PlasmaBalls
     */
    public LinkedList<PlasmaBall> getPlasmaBalls() {
        return plasmaBalls;
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
    public LinkedList<Ship> getShips() {
        return ships;
    }

    /**
     * returns list of all active shot objects
     *
     * @return list of all shots
     */
    public LinkedList<Shot> getShots() {
        return shots;
    }

    /**
     * returns all shown powerups
     *
     * @return list of all powerups
     */
    public LinkedList<PowerUp> getPowerUp() {
        return powerups;
    }

    /**
     * returns list of used Laser (should only be one)
     *
     * @return list of all lasers
     */
    public LinkedList<Laser> getLaser() {
        return lasers;
    }

    /**
     * returns list of all alien objects
     *
     * @return list of all alien objects
     */
    public LinkedList<Alien> getAliens() {
        return aliens;
    }
}
