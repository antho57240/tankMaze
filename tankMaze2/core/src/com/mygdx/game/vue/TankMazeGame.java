package com.mygdx.game.vue;

import com.badlogic.gdx.Game;

public class TankMazeGame extends Game {

	@Override
	public void create() {
		setScreen(new TankMaze());	
	}
}
