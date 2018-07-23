package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by dkotenko on 19.07.2018.
 */
public class Enemy {
    protected Vector2 pos;
    protected int live = 10;
    Texture tx;
    TextureRegion textureAnimation[];
    Animation animation;
    TextureRegion textureSplit[][];
    public boolean remove = false;
    Collision collision;


    protected Enemy(int x, int y) {
        pos = new Vector2(x, y);
    }

    protected Vector2 getPos() {
        return (this.pos);
    }

    public void move() {
        pos.y--;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public int getLive() {
        return live;
    }

    public void render(SpriteBatch batch, float elepsedTime) {
        //batch.draw(tx, pos.x, pos.y);
        batch.draw((TextureRegion) animation.getKeyFrame(elepsedTime, true), pos.x, pos.y);
    }

    public void update() {
        move();
        collision.update(pos.x, pos.y);
        if (pos.y < 0)
            remove = true;
    }

    public Collision getCollision() {
        return collision;
    }

    public int getScore(){
        return 0;
    }
}

//batch.draw((TextureRegion) enemies.get(0).animation.getKeyFrame(elepsedTime, true), enemies.get(0).pos.x, enemies.get(0).pos.y);