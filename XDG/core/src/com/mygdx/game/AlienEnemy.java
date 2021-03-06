package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

/**
 * Created by dkotenko on 7/21/18.
 */
public class AlienEnemy extends Enemy {
    private final int SPEED = 4;
    private final int SCORE = 5;
    private int stopBullet;

    protected AlienEnemy(int x, int y) {
        super(x, y);
        tx = new Texture("aliens.png");
        textureSplit = TextureRegion.split(tx, 100, 184);
        textureAnimation = new TextureRegion[4];
        textureAnimation[0] = textureSplit[0][0];
        textureAnimation[1] = textureSplit[0][1];
        textureAnimation[2] = textureSplit[0][2];
        textureAnimation[3] = textureSplit[0][1];

        animation = new Animation( 1f/1.5f, textureAnimation);

        collision = new Collision(184, 100, x, y);
        live = 4;
        stopBullet = 50;
    }

    @Override
    public void move(){
        pos.y -= SPEED;
    }

    @Override
    public int getScore(){
        return SCORE;
    }

    @Override
    public void checkRemove(Player player) {
        if (collision.CollisionCheck(player.getCollision())) {
            player.setLive(player.getLive() - 15);
            remove = true;
        }
    }

    @Override
    public void update() {
        move();
        collision.update(pos.x, pos.y);
        if (pos.y < -184)
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
