package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by dkotenko on 7/20/18.
 */
public class Collision {
    Vector2 pos;
    int height, width;

    Collision(int height, int width, float x, float y) {
        this.height = height;
        this.width = width;
        pos = new Vector2(x, y);
    }

    public void update(float x, float y) {
        pos.x = x;
        pos.y = y;
    }

    public boolean CollisionCheck(Collision check) {
        return pos.x < check.pos.x + check.width && pos.y < check.pos.y + check.height &&
                pos.x + width > check.pos.x && pos.y + height > check.pos.y;
    }


}
