package de.thdeg.thomas.defender.game.managers;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;
import de.thdeg.thomas.defender.graphics.entities.*;
import de.thdeg.thomas.defender.graphics.level.*;

import java.util.ArrayList;

/**
 * class responsible for Level and game procedure
 */
public class GamePlayManager {

    private final GameView gameView;
    public boolean gameOver;
    ArrayList<CollidableGameObject> collidableGameObjects;
    private final GameObjectManager gameObjectManager;
    private LevelManager levelManager;
    private Level level;
    /**
     * creates new player for game progress
     */
    public Player player;
    public Map map;
    private Overlay overlay;
    private StartScreen startScreen;
    private EndScreen endScreen;
    private int wave;
    private boolean timerHasBeenSet;


    /**
     * responsible for Level and game procedure
     *
     * @param gameView          responsible for visuals
     * @param gameObjectManager responsible for gameObject management
     */
    public GamePlayManager(GameView gameView, GameObjectManager gameObjectManager) {
        this.gameView = gameView;
        collidableGameObjects = new ArrayList<>();
        this.gameObjectManager = gameObjectManager;
        this.overlay = gameObjectManager.getOverlay();
        gameObjectManager.getPlayer().setGamePlayManager(this);
        initializeLevel();


    }

    private void initializeLevel() {
        //this.levelManager = new LevelManager(true);
        this.startScreen = new StartScreen(gameView);
        this.endScreen = new EndScreen(gameView);
        startScreen.startScreen();
        level = new Level();
        player = new Player();
        level.setWave(1);
        gameObjectManager.getPlayer().resetPosition();
        clearObjects();

    }

    private void clearObjects() {
        gameObjectManager.getFarmers().clear();
        gameObjectManager.getAliens().clear();
        gameObjectManager.getShips().clear();
        gameObjectManager.getPlasmaBalls().clear();
    }

    /**
     * updates map and entities
     */
    public void updateGamePlay() {
        spawnAndDestroyEntities();
        if (gameOver) {
            endScreen.showEndScreen("      You lost!\n      The aliens have won!" + "\n      you reached wave:" + wave + "\n      your score is:" + player.score);
            gameOver = false;
            initializeLevel();
        }
    }

    /**
     * destroys selected Alien object
     *
     * @param collidableGameObject : Alien object you want to destroy
     */
    public void destroyAliens(CollidableGameObject collidableGameObject) {
        player.increaseScoreBy(100);
        gameObjectManager.getAliens().remove(collidableGameObject);
    }

    /**
     * destroys selected ship object
     *
     * @param collidableGameObject : ship object you want to destroy
     */
    public void destroyShips(CollidableGameObject collidableGameObject) {
        player.increaseScoreBy(300);
        gameObjectManager.getShips().remove(collidableGameObject);

    }

    /**
     * when a projectile hits the player, this method is used to subtract his health
     *
     * @param damageDone amount of damage that the player should receive
     */
    public void hitPlayer(int damageDone) {
        player.damagePlayerHealth(damageDone);

    }

    /**
     * @return integer of current wave
     */
    public int getWaveLevel() {
        return level.getWave();
    }

    private void spawnAndDestroyEntities() {
        this.wave = level.getWave();
        collidableGameObjects.add(gameObjectManager.getPlayer());
        if (wave == 1) {

            Farmer farmer = new Farmer(gameView, collidableGameObjects);
            Alien alien = new Alien(gameView, farmer);
            alien.setGamePlayManager(this);
            gameObjectManager.getFarmers().add(farmer);
            gameObjectManager.getAliens().add(alien);
            collidableGameObjects.addAll(gameObjectManager.getAliens());
            level.nextWave();
        } else {
            if (gameObjectManager.getAliens().size() <= 0 && gameObjectManager.getShips().size() <= 0) {
                gameObjectManager.getFarmers().clear();
                overlay.ShowMessage("Good Job! Wave:" + Integer.toString(level.getWave()));
                if (!timerHasBeenSet) {
                    gameView.setTimer("wavetimer", "wavetimer", 3000);
                    timerHasBeenSet = true;
                }
                if (gameView.timerExpired("wavetimer", "wavetimer")) {
                    level.nextWave();
                    gameObjectManager.getPlayer().resetPosition();
                    gameObjectManager.updateHUD(wave);
                    spawnWave();
                    timerHasBeenSet = false;
                }
            }

        }

    }

    private void spawnWave() {

        for (int z = 0; z < wave; z++) {
            Farmer farmer = new Farmer(gameView, collidableGameObjects);
            Alien alien = new Alien(gameView, farmer);
            alien.setGamePlayManager(this);
            gameObjectManager.getFarmers().add(farmer);
            gameObjectManager.getAliens().add(alien);
            collidableGameObjects.addAll(gameObjectManager.getAliens());

        }
        for (int z = 0; z < wave / 2; z++) {
            Ship ship = new Ship(gameView);
            ship.setGamePlayManager(this);
            gameObjectManager.getShips().add(ship);

        }

    }


    /**
     * lets chopper shoot 1 rocket at a time
     *
     * @param startPosition sets position where rocket is spawned
     */
    public void shootRocketChopper(Position startPosition, Chopper.Direction direction) {
        ArrayList<CollidableGameObject> collidableGameObjects = new ArrayList<>();
        collidableGameObjects.addAll(gameObjectManager.getShips());
        collidableGameObjects.addAll(gameObjectManager.getAliens());
        Shot shot = new Shot(gameView, collidableGameObjects, direction);
        if (direction == Chopper.Direction.RIGHT) {
            shot.setPosition(new Position(startPosition.x + 30, startPosition.y + 5));
        }
        if (direction == Chopper.Direction.LEFT) {
            shot.setPosition(new Position(startPosition.x + 12, startPosition.y + 2));
        }
        gameObjectManager.getShots().add(shot);
        shot.updatePosition();
        shot.setGamePlayManager(this);


    }

    /**
     * spawns a hostile projectile
     *
     * @param startPosition starting position of projectile
     */
    public void shootPlasmaBall(Position startPosition) {
        ArrayList<CollidableGameObject> collidablePlayer = new ArrayList<>();
        collidablePlayer.add(gameObjectManager.getPlayer());
        PlasmaBall plasmaBall = new PlasmaBall(gameView, collidablePlayer);
        plasmaBall.setPosition(new Position(startPosition.x, startPosition.y + 23));


        gameObjectManager.getPlasmaBalls().add(plasmaBall);
        plasmaBall.updatePosition();
        plasmaBall.setGamePlayManager(this);


    }

    /**
     * deSpawns shot objects that are no longer needed
     */
    public void destroy(Projectile projectile) {

        if (projectile.getClass() == Shot.class) {
            gameObjectManager.getShots().remove(projectile);
        }
        if (projectile.getClass() == PlasmaBall.class) {
            gameObjectManager.getPlasmaBalls().remove(projectile);
        }
    }

    /**
     * responsible for moving the background for the illusion of space
     */
    public void chopperMovingLeft() {
        if (gameObjectManager.getCloudPosition().x <= 0) {
            gameObjectManager.moveWorld(+2, 0);
        }
    }

    /**
     * {@link #chopperMovingLeft}
     */
    public void chopperMovingRight() {
        if (gameObjectManager.getCloudPosition().x >= -1255) {
            gameObjectManager.moveWorld(-2, 0);
        }
    }
}
