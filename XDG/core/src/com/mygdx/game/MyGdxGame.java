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

	ArrayList<SpiderEnemy> enemySpiders;
	ArrayList<AlienEnemy> enemyAliens;
	ArrayList<ShipEnemy> enemyShips;
	ArrayList<BulletAlien> bulletAliens;
	ArrayList<BulletEnemyShip> bulletEnemyShips;
	int bullet_stop;
	float elepsedTime;
	Random random;

	int spownTimer;
	int spownTimerAlien;
	int spownTimerShip;

	BitmapFont font;
	BitmapFont fontEnterEsc;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Backround();
		player = new Player();
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();

		enemySpiders = new ArrayList<SpiderEnemy>();
		enemyAliens = new ArrayList<AlienEnemy>();
		enemyShips = new ArrayList<ShipEnemy>();

		bulletAliens = new ArrayList<BulletAlien>();
		bulletEnemyShips = new ArrayList<BulletEnemyShip>();
		//enemies.add(new SpiderEnemy(40, 700));
		bullet_stop = 8;
		random = new Random(0);
		spownTimer = 0;
		spownTimerAlien = 10;
		spownTimerShip = 20;
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

			if (--spownTimer <= 0) {
				enemySpiders.add(new SpiderEnemy(random.nextInt(740), 1400));
				spownTimer = random.nextInt(30);
			}

			if (--spownTimerAlien <= 0) {
				enemyAliens.add(new AlienEnemy(random.nextInt(720), 1400));
				spownTimerAlien = random.nextInt(260);
			}

			if (--spownTimerShip <= 0) {
				enemyShips.add(new ShipEnemy(80 + random.nextInt(670), 1400));
				spownTimerShip = random.nextInt(500);
			}


		//enemies.get(0).render(batch);
		//batch.draw((TextureRegion) enemies.get(0).animation.getKeyFrame(elepsedTime, true), enemies.get(0).pos.x, enemies.get(0).pos.y);
			for (int i = 0; i < enemySpiders.size(); i++) {
				enemySpiders.get(i).render(batch, elepsedTime);
				enemySpiders.get(i).checkRemove(player);
				for (int k = 0; k < bullets.size(); k++) {
					if (bullets.get(k).getCollision().CollisionCheck(enemySpiders.get(i).getCollision())) {
						bullets.get(k).checkRemove(enemySpiders.get(i), player);
					}
				}
			}
			for (int i = 0; i < bulletAliens.size(); i++) {
				bulletAliens.get(i).render(batch, elepsedTime);
			}

			for (int i = 0; i < bulletEnemyShips.size(); i++) {
				bulletEnemyShips.get(i).render(batch);
			}

			for (int i = 0; i < enemyAliens.size(); i++) {
				if (enemyAliens.get(i).getStopBullet() == 0) {
					bulletAliens.add(new BulletAlien(enemyAliens.get(i).getPos().x + 40, enemyAliens.get(i).getPos().y));
					enemyAliens.get(i).setStopBullet(50);
				}
				enemyAliens.get(i).render(batch, elepsedTime);
				enemyAliens.get(i).checkRemove(player);
				for (int k = 0; k < bullets.size(); k++) {
					bullets.get(k).checkRemove(enemyAliens.get(i), player);
				}
			}

			for (int i = 0; i < enemyShips.size(); i++) {
				if (enemyShips.get(i).getStopBullet() == 0) {
					bulletEnemyShips.add(new BulletEnemyShip(enemyShips.get(i).getPos().x + 10, enemyShips.get(i).getPos().y));
					bulletEnemyShips.add(new BulletEnemyShip(enemyShips.get(i).getPos().x + 70, enemyShips.get(i).getPos().y));
					enemyShips.get(i).setStopBullet(80);
				}
				enemyShips.get(i).render(batch);
				enemyShips.get(i).checkRemove(player);
				for (int k = 0; k < bullets.size(); k++) {
					if (bullets.get(k).getCollision().CollisionCheck(enemyShips.get(i).getCollision())) {
						bullets.get(k).checkRemove(enemyShips.get(i), player);
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

			bulletAliens.removeAll(bulletAliens);
			bulletEnemyShips.removeAll(bulletEnemyShips);
			enemyShips.removeAll(enemyShips);
			enemyAliens.removeAll(enemyAliens);
			enemySpiders.removeAll(enemySpiders);
			bullets.removeAll(bullets);
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


			for (int i = 0; i < bulletEnemyShips.size(); i++) {
				bulletEnemyShips.get(i).checkPlayerCollision(player);
				bulletEnemyShips.get(i).update();
				if (bulletEnemyShips.get(i).remove)
					bulletEnemyShips.remove(bulletEnemyShips.get(i));
			}
			for (int i = 0; i < bulletAliens.size(); i++) {
				bulletAliens.get(i).checkPlayerCollision(player);
				bulletAliens.get(i).update();
				if (bulletAliens.get(i).remove)
					bulletAliens.remove(bulletAliens.get(i));
			}
			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).update();
				if (bullets.get(i).remove)
					bullets.remove(bullets.get(i));
			}


			for (int i = 0; i < enemyShips.size(); i++) {
				enemyShips.get(i).update();
				if (enemyShips.get(i).remove)
					enemyShips.remove(enemyShips.get(i));
			}
			for (int i = 0; i < enemyAliens.size(); i++) {
				enemyAliens.get(i).update();
				if (enemyAliens.get(i).remove)
					enemyAliens.remove(enemyAliens.get(i));
			}
			for (int i = 0; i < enemySpiders.size(); i++) {
				enemySpiders.get(i).update();
				if (enemySpiders.get(i).remove)
					enemySpiders.remove(enemySpiders.get(i));
			}
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
