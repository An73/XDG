package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ShipEnemy extends Enemy {
    private final int SPEED = 4;
    private final int SCORE = 8;
    private int stopBullet;
    private int moveSide;

    protected ShipEnemy(int x, int y) {
        super(x, y);
        tx = new Texture("ShipEnemy.png");

        collision = new Collision(162, 110, x, y);
        live = 6;
        stopBullet = 80;
        moveSide = 200;
    }

    @Override
    public void move(){
        pos.y -= SPEED;
        if (moveSide-- > 100) {
            pos.x -= 1;
        }
        else if (moveSide-- >= 0) {
            pos.x += 1;
            if (moveSide == 0)
                moveSide = 200;
        }
    }

    @Override
    public int getScore(){
        return SCORE;
    }

    @Override
    public void checkRemove(Player player) {
        if (collision.CollisionCheck(player.getCollision())) {
            player.setLive(player.getLive() - 20);
            remove = true;
        }
    }

    @Override
    public void update() {
        move();
        collision.update(pos.x, pos.y);
        if (pos.y < -162)
            remove = true;
        stopBullet--;
    }

    public int getStopBullet() {
        return  stopBullet;
    }

    public void setStopBullet(int stopBullet) {
        this.stopBullet = stopBullet;
    }
}
