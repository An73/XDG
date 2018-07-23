package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by dkotenko on 7/21/18.
 */
public class AlienEnemy extends Enemy {
    private final int SPEED = 4;
    private final int SCORE = 5;

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
    }

    @Override
    public void move(){
        pos.y -= SPEED;
    }

    @Override
    public int getScore(){
        return SCORE;
    }
}
