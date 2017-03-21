package entity;

import tileMap.*;
import java.awt.*;

public class Camera extends MapObject {

	public Camera(TileMap tm) {
		
		super(tm);
		
		//to chyba nawet nie potrzebne
		width = 64;
		height = 64;
		cwidth = 64;
		cheight = 64;
		
		moveSpeed = 1.0;
		maxSpeed = 10.0;
		stopSpeed = 1.0;
	}
	
	private void getNextPosition() {
		
		// movement
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		else {
			if(dx > 0) {
				dx -= stopSpeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += stopSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}		
	}
	
	public void update() {
		
		// update position
		getNextPosition();
		
		checkTileMapCollision();
		setPosition(xtemp, ytemp);	
	}
	
	public void draw(Graphics2D g) {
		
		//to chyba nie potrzebne
		setMapPosition();
		super.draw(g);
		
	}	
}