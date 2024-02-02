package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.ElementVide;
import com.mygdx.game.model.GameElement;
import com.mygdx.game.model.MurBrique;
import com.mygdx.game.model.MurFer;
import com.mygdx.game.model.TankEnnemi;
import com.mygdx.game.model.TankJoueur;
import com.mygdx.game.model.Vegetation;
import com.mygdx.game.model.World;
import com.mygdx.game.vue.TextureFactory;

public class WorldRenderer {
	
	World world = new World();
    static final double ANIM_HERTZ = 0.25;
    float playerAnimationTimeWait = 0;
    int playerAnimationTexture = 0;
	
    public void render(SpriteBatch batch, float deltaTime) {
        checkInputs(deltaTime);

        batch.begin();
        GameElement[][] grid = world.getGrid();

        for (int x = grid.length - 1; x >= 0; --x) {
            for (int y = 0; y < grid[0].length; ++y) {
                TextureRegion tRegion;
                tRegion = getTextureFromGameElement(grid[x][y])[0];
                batch.draw(tRegion, (y * TextureFactory.IMG_SZ)/2F,
                        (grid.length - 1 - x) * TextureFactory.IMG_SZ /2F,
                        grid[x][y].getWidth() / 2F,
                        grid[x][y].getHeight() / 2F);
            }
        }

        TextureRegion[] joueurTextureRegion = TextureFactory.getInstance().getJoueur();
        if (world.getJoueur().moved())
            playerAnimationTimeWait += deltaTime;

        if (playerAnimationTimeWait > ANIM_HERTZ) {
            playerAnimationTimeWait -= ANIM_HERTZ;
            playerAnimationTexture = (playerAnimationTexture + 1) % joueurTextureRegion.length;
        }

        Sprite joueur = new Sprite(joueurTextureRegion[playerAnimationTexture]);

        joueur.setPosition(world.getJoueur().getX(), world.getJoueur().getY());
        joueur.rotate(directionToAngle(world.getJoueur().getDirection()));
        joueur.setScale(0.5F);
        joueur.draw(batch);
        batch.end();
    }
    
    private float directionToAngle(Direction direction) {
        switch (direction) {
            case HAUT:
                return 0;
            case GAUCHE:
                return 90;
            case BAS:
                return 180;
            case DROITE:
                return 270;
            default:
                throw new RuntimeException("Unreachable");
        }
    }
	
	private TextureRegion[] getTextureFromGameElement(GameElement ge) {
    	if (ge instanceof MurBrique) {
    		return TextureFactory.getInstance().getMurBrique1x1();
    	} else if (ge instanceof MurFer) {
    		return TextureFactory.getInstance().getMurFer();
    	} else if (ge instanceof TankJoueur) {
    		return TextureFactory.getInstance().getJoueur();
    	} else if (ge instanceof Vegetation) {
    		return TextureFactory.getInstance().getVegetation();
    	} else if (ge instanceof ElementVide) {
    		return TextureFactory.getInstance().getSol();
    	} else if (ge instanceof TankEnnemi) {
    		return TextureFactory.getInstance().getNPC();
    	} else {
    	return null;
    	}
	}
	
	private void checkInputs(float deltaTime) {
        float modX = 0, modY = 0;
        // go to top
        world.getJoueur().setMoved(false);
        
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
        	modY += 84 * deltaTime;
        	world.getJoueur().setDirection(Direction.HAUT);
        	if(world.getJoueur().getY() < (world.getGrid().length * ((TextureFactory.IMG_SZ - 2) / 2) - (TextureFactory.IMG_SZ / 2))) {
	            world.getJoueur().setMoved(true);
	            world.getJoueur().setY(world.getJoueur().getY() + modY);
        	}      
        }
        
        // go to left
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
        	modX -= 84 * deltaTime;
	        world.getJoueur().setDirection(Direction.GAUCHE);
	        if(world.getJoueur().getX() + (TextureFactory.IMG_SZ / 4) > 0) {
	            world.getJoueur().setMoved(true);
	            world.getJoueur().setX(world.getJoueur().getX() + modX);
        	}    
        }
        
        // go to bottom
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
        	 modY -= 84 * deltaTime;
	         world.getJoueur().setDirection(Direction.BAS);
	         if(world.getJoueur().getY() + (TextureFactory.IMG_SZ / 4) > 0) {
		            world.getJoueur().setMoved(true);
		            world.getJoueur().setY(world.getJoueur().getY() + modY);
	        	}
        }
        
        // go to down
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
        	modX += 84 * deltaTime;
            world.getJoueur().setDirection(Direction.DROITE);
            if(world.getJoueur().getX() < (world.getGrid().length * ((TextureFactory.IMG_SZ - 2) / 2) - (TextureFactory.IMG_SZ / 2))) {
	            world.getJoueur().setMoved(true);
	            world.getJoueur().setX(world.getJoueur().getX() + modX);
        	}   
        }
    }
}
