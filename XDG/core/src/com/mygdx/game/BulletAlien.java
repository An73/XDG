package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by dkotenko on 7/23/18.
 */
public class BulletAlien {
    private final int SPEED = 8;
    private static Texture texture;
    Collision collision;
    float x, y;
    public boolean remove;
    TextureRegion textureSplit[][];
    TextureRegion textureAnimation[];
    Animation       animation;

    public BulletAlien(float x, float y) {
        this.x = x;
        this.y = y;
        remove = false;
        texture = new Texture("BulletAlien.png");
        textureSplit = TextureRegion.split(texture, 20, 20);
        textureAnimation = new TextureRegion[15];
        textureAnimation[0] = textureSplit[0][0];
        textureAnimation[1] = textureSplit[0][1];
        textureAnimation[2] = textureSplit[0][2];
        textureAnimation[3] = textureSplit[0][3];
        textureAnimation[4] = textureSplit[1][0];
        textureAnimation[5] = textureSplit[1][1];
        textureAnimation[6] = textureSplit[1][2];
        textureAnimation[7] = textureSplit[1][3];
        textureAnimation[8] = textureSplit[1][2];
        textureAnimation[9] = textureSplit[1][1];
        textureAnimation[10] = textureSplit[1][0];
        textureAnimation[11] = textureSplit[0][3];
        textureAnimation[12] = textureSplit[0][2];
        textureAnimation[13] = textureSplit[0][1];
        textureAnimation[14] = textureSplit[0][0];

        animation = new Animation(1f/6f, textureAnimation);
        collision = new Collision(18, 18, x, y);
    }

    public void update() {
        y -= SPEED;
        if (y < 0)
            remove = true;
        collision.update(x, y);
    }

    public void render(SpriteBatch batch, float elepsedTime) {
        //batch.draw(tx, pos.x, pos.y);
        batch.draw((TextureRegion) animation.getKeyFrame(elepsedTime, true), x, y);
    }

    public void checkPlayerCollision(Player player) {
        if (collision.CollisionCheck(player.getCollision())) {
            player.setLive(player.getLive() - 2);
            remove = true;
        }
    }
}
