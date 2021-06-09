package de.thdeg.thomas.defender.game.managers;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.gameview.GameView;
import de.thdeg.thomas.defender.graphics.entities.Alien;
import de.thdeg.thomas.defender.graphics.entities.CollidableGameObject;
import de.thdeg.thomas.defender.graphics.entities.Ship;
import de.thdeg.thomas.defender.graphics.entities.Shot;

import java.util.ArrayList;

/**
 * class responsible for level and game procedure
 */
public class GamePlayManager {
    private final GameView gameView;
    private final GameObjectManager gameObjectManager;
    private boolean listHasBeenDeleted;
    private boolean listHasBeenDeletedShips;


    /**
     * responsible for level and game procedure
     *
     * @param gameView
     * @param gameObjectManager
     */
    public GamePlayManager(GameView gameView, GameObjectManager gameObjectManager) {
        this.gameView = gameView;
        this.gameObjectManager = gameObjectManager;
        gameObjectManager.getPlayer().setGamePlayManager(this);
        listHasBeenDeleted = false;
        listHasBeenDeletedShips = false;

    }

    /**
     * updates map and entities
     */
    public void updateGamePlay() {
        spawnAndDestroyAliens();
        spawnAndDestroyShips();
    }

    /**
     * desotroys selected Alien object
     *
     * @param collidableGameObject : Alien object you want to destroy
     */
    public void destroyAliens(CollidableGameObject collidableGameObject) {

        gameObjectManager.getAliens().remove(collidableGameObject);


    }

    /**
     * desotroys selected ship object
     *
     * @param collidableGameObject : ship object you want to destroy
     */
    public void destroyShips(CollidableGameObject collidableGameObject) {
        gameObjectManager.getShips().remove(collidableGameObject);

    }

    void spawnAndDestroyAliens() {

        if (!listHasBeenDeleted && (gameView.getGameTimeInMilliseconds() >= 10000)) {
            gameObjectManager.getAliens().clear();
            listHasBeenDeleted = true;
        }
        if (gameView.timerExpired("alientimer", "GamePlayManager")) {
            gameView.setTimer("alientimer", "GamePlayManager", 10000);
            Alien alien = new Alien(gameView);
            gameObjectManager.getAliens().add(alien);
            System.out.print("created");
        }
        if (gameObjectManager.getAliens().size() >= 3) {
            System.out.print("removed");
            gameObjectManager.getAliens().removeFirst();
        }
        if (gameObjectManager.getAliens().size() >= 1) {
            if (gameView.timerExpired("destruction", "destruction")) {
                gameObjectManager.getAliens().remove((Math.random() * (gameObjectManager.getAliens().size() - 0)) + 0);
            }
            gameView.setTimer("destruction", "destruction", 5000);

        }
    }

    void spawnAndDestroyShips() {

        if (!listHasBeenDeletedShips && (gameView.getGameTimeInMilliseconds() >= 10000)) {
            gameObjectManager.getShips().clear();
            listHasBeenDeletedShips = true;
        }

        if (gameView.timerExpired("shiptimer", "GamePlayManager")) {
            gameView.setTimer("shiptimer", "GamePlayManager", 10000);

            Ship ship = new Ship(gameView);
            gameObjectManager.getShips().add(ship);
            System.out.print("created");
        }
        if (gameObjectManager.getShips().size() >= 3) {
            System.out.print("removed");
            gameObjectManager.getShips().removeFirst();
        }
        if (gameObjectManager.getShips().size() >= 1) {
            if (gameView.timerExpired("destructionShip", "destruction")) {
                gameObjectManager.getShips().remove((Math.random() * (gameObjectManager.getShips().size() - 0)) + 0);
            }
            gameView.setTimer("destructionShip", "destruction", 5000);

        }

    }

    /**
     * lets chopper shoot 1 rocket at a time
     *
     * @param startPosition sets position where rocket is spawned
     */
    public void shootRocketChopper(Position startPosition) {
        ArrayList<CollidableGameObject> collidableGameObjects = new ArrayList<>();
        collidableGameObjects.addAll(gameObjectManager.getShips());
        collidableGameObjects.addAll(gameObjectManager.getAliens());
        Shot shot = new Shot(gameView, collidableGameObjects);
        shot.setPosition(new Position(startPosition.x, startPosition.y + 23));
        System.out.println("shoot!");

        gameObjectManager.getShots().add(shot);
        shot.updatePosition();
        shot.setGamePlayManager(this);


    }

    /**
     * despawns shot objects that are no longer needed
     *
     * @param shot describes shot that should be deleted
     */
    public void destroy(Shot shot) {
        System.out.println("deleted");
        gameObjectManager.getShots().remove(shot);
    }

    /**
     * responsible for moving the background for the illusion of space
     * @param speedInPixel
     */
    public void chopperMovingLeft(double speedInPixel) {
        gameObjectManager.moveWorld(+2, 0);
    }

    /**
     * {@link #chopperMovingLeft}
     */
    public void chopperMovingRight(double speedInPixel) {
        gameObjectManager.moveWorld(-2, 0);
    }
}
