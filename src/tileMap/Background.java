package tileMap;

import main.GamePanel;

import java.awt.*;

import gameState.MenuImg;

public class Background {
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	public static double xLast;
	public static double yLast;
	
	
	private double moveScale;
	
	public Background(double ms) {
		
		moveScale = ms;
		this.x = xLast;
		this.y = yLast;
		
	}
	
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % GamePanel.WIDTH;
		this.y = (y * moveScale) % GamePanel.HEIGHT;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update() {
		x += dx;
		y += dy;
		
		xLast = x;
		yLast = y;
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(MenuImg.menuBackgroundScaled, (int)x, (int)y, null);
		
		if(x == - GamePanel.WIDTH || x == GamePanel.WIDTH)
			x = 0;
		if(x < 0) {
			g.drawImage(
					MenuImg.menuBackgroundScaled,
				(int)x + GamePanel.WIDTH,
				(int)y,
				null
			);
		}
		if(x > 0) {
			g.drawImage(
					MenuImg.menuBackgroundScaled,
				(int)x - GamePanel.WIDTH,
				(int)y,
				null
			);
		}
	}
}