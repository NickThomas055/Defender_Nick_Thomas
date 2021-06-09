package de.thdeg.thomas.defender.graphics.entities;

import de.thdeg.thomas.defender.gameview.GameView;

import java.util.ArrayList;

/**
 * parent class for any kind of projectile(rocket, shell , debris)
 */
abstract public class Projectile extends CollidingGameObject{
    Projectile(GameView gameView,ArrayList<CollidableGameObject> collidableGameObject){
        super(gameView,collidableGameObject);
    }
    @Override
    public void updateHitBoxPosition(){
        this.hitBox.x = (int)this.position.x;
        this.hitBox.y = (int)this.position.y+10;

    }

    @Override
    public void reactToCollision(CollidableGameObject otherObject) {
        System.out.println("hit!");


    }
}
