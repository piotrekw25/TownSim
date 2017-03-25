package gameState;

import main.GamePanel;
import tileMap.*;
import entity.*;
//import entity.Enemies.*;
import audio.AudioPlayer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PlayState extends GameState {

	private TileMap tileMap;
	private Background bg;

	private Camera camera;
	
	private Road road;

	//private ArrayList<Enemy> enemies;
	//private ArrayList<Explosion> explosions;

	private HUD hud;

	//private AudioPlayer bgMusic;

	private int tileSize = 64;
	

	public PlayState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {

		tileMap = new TileMap(tileSize);
		tileMap.loadTiles("/tilesets/tileset" + tileSize + ".png");
		tileMap.loadMap("/maps/map1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);

		 //bg = new Background("/Backgrounds/grassbg1.gif", 0.1);

		camera = new Camera(tileMap);
		camera.setPosition(1000, 1000);

		populateEntities();

		//explosions = new ArrayList<Explosion>();

		hud = new HUD(camera);
		road = new Road(tileMap, 100, 100, 100, 100);

		//bgMusic = new AudioPlayer("/Music/level1-1.mp3");
		// bgMusic.play();

	}

	private void populateEntities() {

//		enemies = new ArrayList<Enemy>();
//
//		Slugger s;
//		Point[] points = new Point[] { new Point(200, 100), new Point(860, 200), new Point(1525, 200),
//				new Point(1680, 200), new Point(1800, 200) };
//		for (int i = 0; i < points.length; i++) {
//			s = new Slugger(tileMap);
//			s.setPosition(points[i].x, points[i].y);
//			enemies.add(s);
//		}

	}

	public void update() {

		// update player
		camera.update();

		tileMap.setPosition(GamePanel.WIDTH / 2 - camera.getx(), GamePanel.HEIGHT / 2 - camera.gety());

		// set background
		//bg.setPosition(tileMap.getx(), tileMap.gety());

		// attack enemies

		// update all enemies
//		for (int i = 0; i < enemies.size(); i++) {
//			Enemy e = enemies.get(i);
//			e.update();
//			if (e.isDead()) {
//				enemies.remove(i);
//				i--;
//				explosions.add(new Explosion(e.getx(), e.gety()));
//			}
//		}

	}

	public void draw(Graphics2D g) {

		// draw bg
		//bg.draw(g);

		// draw tilemap
		tileMap.draw(g);
		
		hud.draw(g);
		
		road.draw(g);
		//draw hud
		
		
		// draw enemies
//		for (int i = 0; i < enemies.size(); i++) {
//			enemies.get(i).draw(g);
//		}
//
//		// draw explosions
//		for (int i = 0; i < explosions.size(); i++) {
//			explosions.get(i).setMapPosition((int) tileMap.getx(), (int) tileMap.gety());
//			explosions.get(i).draw(g);
//		}
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_A)
			camera.setLeft(true);
		if (k == KeyEvent.VK_D)
			camera.setRight(true);
		if (k == KeyEvent.VK_W)
			camera.setUp(true);
		if (k == KeyEvent.VK_S)
			camera.setDown(true);
		
		if (k == KeyEvent.VK_1)
			hud.setPanel(hud.roadsPanel);
		if (k == KeyEvent.VK_2)
			hud.setPanel(hud.zonesPanel);
		if (k == KeyEvent.VK_3)
			hud.setPanel(hud.electricityPanel);
		if (k == KeyEvent.VK_4)
			hud.setPanel(hud.waterPanel);
		
	}

	public void keyReleased(int k) {
		if (k == KeyEvent.VK_A)
			camera.setLeft(false);
		if (k == KeyEvent.VK_D)
			camera.setRight(false);
		if (k == KeyEvent.VK_W)
			camera.setUp(false);
		if (k == KeyEvent.VK_S)
			camera.setDown(false);
	}
}