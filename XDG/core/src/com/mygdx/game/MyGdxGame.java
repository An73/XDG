package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Backround background;
	Player 	player;
	ArrayList<Bullet> bullets;
	ArrayList<Enemy> enemies;
	int bullet_stop;
	float elepsedTime;
	Random random;

	int spownTimer;
	int spownTimerAlien;

	BitmapFont font;
	BitmapFont fontEnterEsc;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Backround();
		player = new Player();
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
		//enemies.add(new SpiderEnemy(40, 700));
		bullet_stop = 8;
		random = new Random(0);
		spownTimer = 0;
		spownTimerAlien = 10;
		font = new BitmapFont(Gdx.files.internal("./font/gameover.fnt"));
		fontEnterEsc = new BitmapFont(Gdx.files.internal("./font/enteresc.fnt"));


	}

	@Override
	public void render () {
		elepsedTime += Gdx.graphics.getDeltaTime();

		update();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);
		if (player.getLive() > 0) {
			//player.render(batch);
			if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
				player.setLive(0);

			if (--spownTimerAlien <= 0) {
				enemies.add(new AlienEnemy(random.nextInt(720), 1400));
				spownTimerAlien = random.nextInt(150);
			}

			if (--spownTimer <= 0) {
				enemies.add(new SpiderEnemy(random.nextInt(740), 1400));
				spownTimer = random.nextInt(30);
			}


		//enemies.get(0).render(batch);
		//batch.draw((TextureRegion) enemies.get(0).animation.getKeyFrame(elepsedTime, true), enemies.get(0).pos.x, enemies.get(0).pos.y);

			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).render(batch, elepsedTime);
				if (enemies.get(i).getCollision().CollisionCheck(player.getCollision())) {
					player.setLive(player.getLive() - 10);
					enemies.get(i).remove = true;
				}
				for (int k = 0; k < bullets.size(); k++) {
					if (bullets.get(k).getCollision().CollisionCheck(enemies.get(i).getCollision())) {
						bullets.get(k).remove = true;
						enemies.get(i).remove = true;
						player.setScore(enemies.get(i).getScore() + player.getScore());
					}
				}
			}

			if ((Gdx.input.isKeyPressed(Input.Keys.SPACE)) && --bullet_stop == 0) {
		   	 	bullets.add(new Bullet(player.getPos().x + 24, player.getPos().y + 70));
		    	bullet_stop = 8;
        	}

        	for(int i = 0; i < bullets.size(); i++) {
            	bullets.get(i).render(batch);
        	}
			player.render(batch);
			batch.draw(new Texture("medical.png"), 10, 10);
		}
		else {
			font.draw(batch, "GAME\nOVER", 240, 800);
			fontEnterEsc.draw(batch, String.format("Your score\n%8d", player.getScore()), 320, 570);
			fontEnterEsc.draw(batch, "Press \"Enter\" to restart", 210, 510);
			fontEnterEsc.draw(batch, "Press \"q\" to quit", 270, 480);
			for (int i = 0; i < enemies.size(); i++)
				enemies.remove(enemies.get(i));
			for (int i = 0; i < bullets.size(); i++)
				bullets.remove(bullets.get(i));
			if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
				player.setLive(100);
				player.setScore(0);
				player.setPos(370, 100);
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
				Gdx.app.exit();
			}
		}
		batch.end();
	}

	public void update(){
		background.update();
		if (player.getLive() > 0) {
			player.update();

			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).update();
				if (bullets.get(i).remove)
					bullets.remove(bullets.get(i));
			}
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).update();
				if (enemies.get(i).remove)
					enemies.remove(enemies.get(i));
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
