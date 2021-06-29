package de.thdeg.thomas.defender.graphics.level;

import de.thdeg.thomas.defender.game.Position;
import de.thdeg.thomas.defender.game.managers.GameObjectManager;
import de.thdeg.thomas.defender.gameview.GameView;
import de.thdeg.thomas.defender.graphics.entities.Alien;
import de.thdeg.thomas.defender.graphics.entities.Farmer;
import de.thdeg.thomas.defender.graphics.entities.Ship;

import java.awt.*;
import java.util.LinkedList;


public class Map extends Background {
    GameObjectManager gameObjectManager;
    LinkedList<Alien> aliens;
    LinkedList<Ship> ships;
    LinkedList<Farmer> farmers;
    Position playerPosition;

    public Map(GameView gameView, GameObjectManager gameObjectManager) {
        super(gameView);
        this.gameObjectManager = gameObjectManager;
        this.position = new Position(GameView.WIDTH/2d-50, GameView.HEIGHT - 70);


    }

    @Override
    protected void updateStatus() {
        this.aliens = gameObjectManager.getAliens();
        this.ships = gameObjectManager.getShips();
        this.farmers =gameObjectManager.getFarmers();

    }
    @Override
    public void adaptPosition(double adaptX, double adaptY) {
        position.x += adaptX*0.125;
    }

    @Override
    public void addToCanvas() {
        this.playerPosition = calculatePositionOnMap(gameObjectManager.getPlayer().getPosition());
        //border
        gameView.addRectangleToCanvas(GameView.WIDTH/2d-GameView.WIDTH*0.125-50, GameView.HEIGHT - 70, GameView.WIDTH*0.375, GameView.HEIGHT*0.125, 2, false, Color.WHITE);
        //player
        gameView.addOvalToCanvas(playerPosition.x,playerPosition.y,4,4,2,true,Color.CYAN);
        //hills
        gameView.addImageToCanvas("hillsMap.png",position.x,position.y+2,0.625,0);
        gameView.addRectangleToCanvas(0, GameView.HEIGHT - 70, GameView.WIDTH/2d-GameView.WIDTH*0.125-50, GameView.HEIGHT*0.125, 2, true, Color.BLACK);
        gameView.addRectangleToCanvas((GameView.WIDTH/2d-GameView.WIDTH*0.125-50)*2+51, GameView.HEIGHT - 70, GameView.WIDTH/2d-GameView.WIDTH*0.125-50, GameView.HEIGHT*0.125, 2, true, Color.BLACK);
        //visible area
        gameView.addRectangleToCanvas(GameView.WIDTH/2d-50, GameView.HEIGHT - 70, GameView.WIDTH*0.125, GameView.HEIGHT*0.125, 2, false, Color.WHITE);
        //aliens
        for(Alien alien: aliens){
            Position alienpos = calculatePositionOnMap(alien.getPosition());
            gameView.addOvalToCanvas(alienpos.x,alienpos.y,2,2,2,true,Color.white);
        }
        //ships
        for(Ship ship : ships){
            Position shippos = calculatePositionOnMap(ship.getPosition());
            gameView.addOvalToCanvas(shippos.x,shippos.y,3,3,2,true,Color.white);
        }
        for(Farmer farmer : farmers){
            Position farmerpos = calculatePositionOnMap(farmer.getPosition());
            gameView.addOvalToCanvas(farmerpos.x,farmerpos.y,2,2,2,true,Color.green);
        }

    }
    private Position calculatePositionOnMap(Position p){
        return new Position(GameView.WIDTH/2d-50+p.x*0.125, GameView.HEIGHT - 70+p.y*0.125);
    }
}
