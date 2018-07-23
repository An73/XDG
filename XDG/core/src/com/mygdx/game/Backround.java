package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Backround {

    class BG {
        private Texture tx;
        private Vector2 pos;

        public BG(Vector2 pos) {
            tx = new Texture("backgroundFull.jpeg");
            this.pos = pos;
        }
    }

    private int speed;
    private BG bg[];
    public Backround() {
        speed = 3;
        bg = new BG[2];
        bg[0] = new BG(new Vector2(0, 0));
        bg[1] = new BG(new Vector2(0, 1400));
    }

    public void render(SpriteBatch batch) {
        batch.draw(bg[0].tx, bg[0].pos.x, bg[0].pos.y);
        batch.draw(bg[1].tx, bg[1].pos.x, bg[1].pos.y);
    }

    public void update(){
        bg[0].pos.y -= speed;
        bg[1].pos.y -= speed;
        if (bg[0].pos.y < -1400) {
            bg[0].pos.y = 0;
            bg[1].pos.y = 1400;
        }
    }
}
