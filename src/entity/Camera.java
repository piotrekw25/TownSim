package entity;

import java.awt.Graphics2D;

import tileMap.*;

public class Camera extends MapObject {

	public Camera(TileMap tm) {

		super(tm);

		// to chyba nawet nie potrzebne
		width = 64;
		height = 64;
		cwidth = 64;
		cheight = 64;

		moveSpeed = 10.0;
		maxSpeed = 10.0;
		stopSpeed = 10.0;
	}

	private void getNextPosition() {

		// movement
		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else if (up) {
			dy -= moveSpeed;
			if (dy < -maxSpeed) {
				dy = -maxSpeed;
			}
		} else if (down) {
			dy += moveSpeed;
			if (dy > maxSpeed) {
				dy = maxSpeed;
			}
		} else {
			if (dx > 0) {
				dx -= stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			} else if (dx < 0) {
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			} else if (dy > 0) {
				dy -= stopSpeed;
				if (dy < 0) {
					dy = 0;
				}
			} else if (dy < 0) {
				dy += stopSpeed;
				if (dy > 0) {
					dy = 0;
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
}