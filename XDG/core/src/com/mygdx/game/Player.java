package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Texture tx;
    private Vector2 pos;
    private int     live;
    private String  strLive;
    private int     score;
    private Collision collision;
    final private int SPEED = 7;

    BitmapFont font;
    BitmapFont fontScore;


    public Player() {
        tx = new Texture("1B.png");
        pos = new Vector2(370, 100);
        live = 100;
        collision = new Collision(71, 45, pos.x, pos.y);
        strLive = new String(Integer.toString(live));
        font = new BitmapFont(Gdx.files.internal("./font/fontlive.fnt"));
        fontScore = new BitmapFont(Gdx.files.internal("./font/score.fnt"));
        score = 0;
    }

    public void render(SpriteBatch batch) {
        batch.draw(tx, pos.x, pos.y);
        font.draw(batch, strLive, 75, 50);
        fontScore.draw(batch, String.format("Score:\n%d", score), 610, 65);
    }

    public void control() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && pos.y < 1240)
            this.pos.y += SPEED;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && pos.y > 0)
            this.pos.y -= SPEED;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && pos.x < 735)
            this.pos.x += SPEED;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && pos.x > 0)
            this.pos.x -= SPEED;
    }

    public void update() {
        control();
        collision.update(pos.x, pos.y);
        strLive = Integer.toString(live);
    }

    public void setPos(float x, float y){
        pos.x = x;
        pos.y = y;
    }

    public Vector2 getPos() {
        return pos;
    }

    public Collision getCollision() {
        return collision;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public int getLive() {
        return live;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }


}
