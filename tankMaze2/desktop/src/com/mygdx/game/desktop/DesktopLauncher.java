package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.vue.TankMaze;
import com.mygdx.game.vue.TankMazeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 840;
		config.useGL30 = true;
		config.height = 840;
		new LwjglApplication(new TankMazeGame(), config);
	}
}
