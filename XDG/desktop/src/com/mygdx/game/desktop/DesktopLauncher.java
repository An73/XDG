package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MainMenu;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		System.setProperty("user.name","seconduser");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 798;
		config.height = 1400;
		new LwjglApplication(new MyGdxGame(), config);
		//MainMenu menu = new MainMenu(config);
		//menu.render(1);
	}
}
