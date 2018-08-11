package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dkotenko on 19.07.2018.
 */
public class SpiderEnemy extends Enemy {
    private final int SPEED = 5;
    private final int SCORE = 1;

    SpiderEnemy(int x, int y) {
        super(x, y);
        tx = new Texture("spider03.png");
        textureSplit = TextureRegion.split(tx, 64, 64);
        textureAnimation = new TextureRegion[6];
        textureAnimation[0] = textureSplit[4][1];
        textureAnimation[1] = textureSplit[4][2];
        textureAnimation[2] = textureSplit[4][3];
        textureAnimation[3] = textureSplit[4][4];
        textureAnimation[4] = textureSplit[4][3];
        textureAnimation[5] = textureSplit[4][2];

        animation = new Animation(1f/8f, textureAnimation);

        collision = new Collision(44, 44, x, y);
        live = 0;
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
    public void checkRemove(Player player){
        if (collision.CollisionCheck(player.getCollision())) {
            player.setLive(player.getLive() - 10);
            remove = true;
        }
    }
    @Override
    public void update() {
        move();
        collision.update(pos.x, pos.y);
        if (pos.y < -64)
            remove = true;
    }

    /*public void animation() {
        tr[0] = tmptr[0][0];
        tr[1] = tmptr[0][6];
        tr[2] = tmptr[0][7];

        animation = new Animation(1f/4f, tr);
    }*/
}
