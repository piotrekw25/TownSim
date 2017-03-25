package entity;

import tileMap.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Road extends MapObject {

	private int xStart;
	private int yStart;
	private int xEnd;
	private int yEnd;
	
	
	private BufferedImage[] tile;
	
	public Road(TileMap tm, int xStart, int yStart, int xEnd, int yEnd) {

		super(tm);

		width = 64;
		height = 64;

		// load sprites
		try {

			BufferedImage tileset = ImageIO.read(getClass().getResourceAsStream("/tilesets/roads/smallRoad.png"));

			tile = new BufferedImage[tileset.getWidth(null) / width];
			
			for (int i = 0; i < tile.length; i++) {
				tile[i] = tileset.getSubimage(i * width, 0, width, height);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
		
		this.x = 300;
		this.y = 400;
		this.xmap = 500.0;
		this.ymap = 500.0;
		this.setPosition(300,500);

	}

	public void update() {

		checkTileMapCollision();
		setPosition(xtemp, ytemp);
	}

	public void draw(Graphics2D g) {
		g.drawImage(tile[1], (int)xmap, (int)ymap, null);
		setMapPosition();

		super.draw(g);
		
		

	}

}
