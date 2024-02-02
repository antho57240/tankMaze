package com.mygdx.game.model;

import com.mygdx.game.controller.Direction;

public class ElementDyn extends GameElement {
	
	private boolean moved = false;
	private float x, y;
	Direction direction = Direction.HAUT;

	public Direction getDirection() {
	    return direction;
	}
	
	public void setDirection(Direction direction) {
	    this.direction = direction;
	}

	public float getX() {
	    return x;
	}
	
	public float getY() {
	    return y;
	}
	
	public void setX(float x) {
	    this.x = x;
	}
	
	public void setY(float y) {
	    this.y = y;
	}
	
	public boolean moved() { 
		return moved; 
	} 
	
	public void setMoved(boolean m) { 
		moved = m; 
	} 
}
