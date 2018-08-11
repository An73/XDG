package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BulletEnemyShip {
    private final int SPEED = 8;
    private Texture texture;
    private Collision collision;
    float x, y;
    public boolean remove;

    public BulletEnemyShip(float x, float y) {
        this.x = x;
        this.y = y;
        remove = false;
        texture = new Texture("BulletShip.png");
        collision = new Collision(60, 15, x, y);
    }

    public void update() {
        y -= SPEED;
        if (y < -60)
            remove = true;
        collision.update(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void checkPlayerCollision(Player player) {
        if (collision.CollisionCheck(player.getCollision())) {
            player.setLive(player.getLive() - 3);
            remove = true;
        }
    }
}
