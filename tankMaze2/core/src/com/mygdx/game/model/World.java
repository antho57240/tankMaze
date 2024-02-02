package com.mygdx.game.model;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;


public class World {
	
	private GameElement[][] grid = {};
	private TankJoueur joueur = new TankJoueur();
    private ArrayList<TankEnnemi> ennemis = new ArrayList<TankEnnemi>();
	
	/**
	 * Instancie le monde
	 */
	
	public World() {
		Json json = new Json();

		String text = Gdx.files.local("config/world.json").readString();
		grid = json.fromJson(grid.getClass(), text);

		String textPlayer = Gdx.files.local("config/player.json").readString();
		float[] playerPosition = {};
		playerPosition = json.fromJson(playerPosition.getClass(), textPlayer);
		joueur.setX(playerPosition[0]);
        joueur.setY(playerPosition[1]);

        String textEnnemi = Gdx.files.local("config/ennemis.json").readString();

        float[][] ennemisPos = {};
        ennemisPos = json.fromJson(ennemisPos.getClass(), textEnnemi);
        for (float[] ennemi : ennemisPos) {
            TankEnnemi npc = new TankEnnemi();
            npc.setX(ennemi[0]);
            npc.setY(ennemi[1]);
            ennemis.add(npc);
        }
	}
	
	public GameElement[][] getGrid() {
		return grid;
	}
	
	public TankJoueur getJoueur() {
        return joueur;
    }
}
