package gameState;

import main.GamePanel;
import tileMap.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PlayState extends GameState {
	
	private TileMap tileMap;
	
	private int tileSize = 64;
	
	public PlayState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		
		tileMap = new TileMap(tileSize);
		tileMap.loadTiles("/tilesets/ground-set.gif");
		tileMap.loadMap("/maps/map1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g) {
		tileMap.draw(g);		
	}
	
	public void keyPressed(int k) {
/*		if(k == KeyEvent.VK_LEFT) camera.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) camera.setRight(true);
		if(k == KeyEvent.VK_UP) camera.setUp(true);
		if(k == KeyEvent.VK_DOWN) camera.setDown(true);
		if(k == KeyEvent.VK_W) camera.setJumping(true);
		if(k == KeyEvent.VK_E) camera.setGliding(true);
		if(k == KeyEvent.VK_R) camera.setScratching();
		if(k == KeyEvent.VK_F) camera.setFiring();*/
	}
	
	public void keyReleased(int k) {
/*		if(k == KeyEvent.VK_LEFT) camera.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) camera.setRight(false);
		if(k == KeyEvent.VK_UP) camera.setUp(false);
		if(k == KeyEvent.VK_DOWN) camera.setDown(false);
		if(k == KeyEvent.VK_W) camera.setJumping(false);
		if(k == KeyEvent.VK_E) camera.setGliding(false);*/
	}
	
}