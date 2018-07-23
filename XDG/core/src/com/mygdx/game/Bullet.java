package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {
    public static final int SPEED = 9;
    private static Texture texture;
    Collision collision;

    float x, y;
    public boolean remove = false;

    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;
        texture = new Texture("bullet.png");
        collision = new Collision(12, 12, x, y);
    }

    public void update() {
        y += SPEED;
        if (y > 1400)
            remove = true;
        collision.update(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public Collision getCollision() {
        return collision;
    }
}
