package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import gameState.PlayState;
import gameState.Values;
import main.GamePanel;

public class HUD {

	private Camera camera;

	private BufferedImage image;
	private BufferedImage circle;
	private Font font;

	private int hudX;
	private int hudY;

	private int circleSpacing = 80;
	private int circleNum = 13;
	private int circleWidth;
	
	private int panelOffset = 300;

	String date;
	
	public int roadsPanel = 0;
	public int zonesPanel = 1;
	
	public int electricityPanel = 2;
	public int waterPanel = 3;
	public int garbagePanel = 4;
	
	public int healthPanel = 5;
	public int firePanel = 6;
	public int policePanel = 7;
	
	public int educationPanel = 8;
	public int transportPanel = 9;
	public int parksPanel = 10;
	
	public int budgetPanel = 11;
	public int policiesPanel = 13;
	
	private int currentPanel = 99;
	
	private BufferedImage iconRoads;
	private BufferedImage iconZones;

	private BufferedImage iconElectricity;
	private BufferedImage iconWater;
	private BufferedImage iconGarbage;

	private BufferedImage iconHealth;
	private BufferedImage iconFire;
	private BufferedImage iconPolice;

	private BufferedImage iconEducation;
	private BufferedImage iconTransport;
	private BufferedImage iconParks;

	private BufferedImage iconBudget;
	private BufferedImage iconPolicies;
	
	private BufferedImage[] panels = new BufferedImage[circleNum];


	public HUD(Camera c) {
		camera = c;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/ui/hud.png"));
			circle = ImageIO.read(getClass().getResourceAsStream("/ui/circle.png"));
			
			panels[0] = iconRoads = ImageIO.read(getClass().getResourceAsStream("/ui/icons/roads.png"));
			panels[1] = iconZones = ImageIO.read(getClass().getResourceAsStream("/ui/icons/zones.png"));
			
			panels[2] = iconElectricity = ImageIO.read(getClass().getResourceAsStream("/ui/icons/electricity.png"));
			panels[3] = iconWater = ImageIO.read(getClass().getResourceAsStream("/ui/icons/water.png"));
			panels[4] = iconGarbage = ImageIO.read(getClass().getResourceAsStream("/ui/icons/garbage.png"));
			
			panels[5] = iconHealth = ImageIO.read(getClass().getResourceAsStream("/ui/icons/health.png"));
			panels[6] = iconFire = ImageIO.read(getClass().getResourceAsStream("/ui/icons/fire.png"));
			panels[7] = iconPolice = ImageIO.read(getClass().getResourceAsStream("/ui/icons/police.png"));
			
			panels[8] = iconEducation = ImageIO.read(getClass().getResourceAsStream("/ui/icons/education.png"));
			panels[9] = iconTransport = ImageIO.read(getClass().getResourceAsStream("/ui/icons/transport.png"));
			panels[10] = iconParks = ImageIO.read(getClass().getResourceAsStream("/ui/icons/parks.png"));
			
			panels[11] = iconBudget = ImageIO.read(getClass().getResourceAsStream("/ui/icons/budget.png"));
			panels[12] = iconPolicies = ImageIO.read(getClass().getResourceAsStream("/ui/icons/policies.png"));
			
			font = new Font("Boulder", Font.PLAIN, 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
		hudX = GamePanel.WIDTH / 2 - image.getWidth(null) / 2;
		hudY = GamePanel.HEIGHT - image.getHeight(null);
		circleWidth = circle.getWidth();

	}

	public void draw(Graphics2D g) {
		
		g.setFont(font);
		
		g.setColor(Color.RED);
		if (Values.waterUsed > Values.water) {
			g.drawString("Not enough water!", 32, 1 * 32);
			
		}
		
		if(Values.electricityUsed > Values.electricity) {
			g.drawString("Not enough electricity!", 32, 2 * 32);
		}
		
		if(Values.availableJobs < Values.homes) {
			g.drawString("Not enough jobs!", 32, 3 * 32);
		}
		
		g.drawImage(image, hudX, hudY, null);
		
		g.setColor(Color.BLACK);

		//panell selection icons
//		g.drawImage(image, hudX, hudY, null);
//		for (int i = 0; i < circleNum; i++) {
//			g.drawImage(circle, ((hudX + image.getWidth() / 2) - circleWidth / 2) - ((circleNum - 1) * circleSpacing) / 2 + i * circleSpacing, hudY - 40, null);
//			g.drawImage(panels[i], ((hudX + image.getWidth() / 2) - circleWidth / 2) - ((circleNum - 1) * circleSpacing) / 2 + i * circleSpacing + 8, hudY - 40 + 8, null);
//		}

		leftPanel(g);

		if (currentPanel == roadsPanel)
			panel1(g);
		if(currentPanel == zonesPanel)
			panel2(g);
		if(currentPanel == electricityPanel)
			panel3(g);
		if(currentPanel == waterPanel)
			panel4(g);
			

	}

	public void leftPanel(Graphics2D g) {
		g.drawString("Money: " + Values.money + "$", hudX + 50, hudY + 45 + 0 * 32);
		g.drawString("Population: " + Values.population, hudX + 50, hudY + 45 + 1 * 32);
		g.drawString("Day: " + Values.day, hudX + 50, hudY + 45 + 2 * 32);
		
		g.drawString("Power: " + Values.electricityUsed + "/" + Values.electricity + "MW/day", hudX + 250, hudY + 45 + 0 * 32);
		g.drawString("Water: " + Values.waterUsed + "/" + Values.water + " L/day", hudX + 250, hudY + 45 + 1 * 32);
		
		g.drawString("Jobs: " + Values.availableJobs, hudX + 550, hudY + 45 + 0 * 32);
		g.drawString("Daily income: " + Values.dailyTaxes + "$", hudX + 550, hudY + 45 + 1 * 32);
		g.drawString("Daily expenses: " + Values.dailyMaintenace + "$", hudX + 550, hudY + 45 + 2 * 32);
		
		g.setColor(Color.BLACK);
		g.fillRect(hudX + 158, hudY + 90, 54, 20);
		
		
		int dayBar = (int) (PlayState.elapsed * 100 / Values.dayLength / 2);
		
		g.setColor(Color.ORANGE);
		g.fillRect(hudX + 160, hudY + 92, dayBar, 16);
		
	}
	
	public void panel1(Graphics2D g) { //Roads
		g.drawString("Roads", hudX + panelOffset, hudY + 45 + 0 * 32);
	}
	
	public void panel2(Graphics2D g) { //Zones
		g.drawString("Zones", hudX + panelOffset, hudY + 45 + 0 * 32);
	}
	public void panel3(Graphics2D g) { //Electricity
		
	}
	public void panel4(Graphics2D g) { //Water
		
	}
	public void panel5(Graphics2D g) { //Water
		
	}
	public void panel6(Graphics2D g) { //Water
		
	}
	public void panel7(Graphics2D g) { //Water
		
	}
	public void panel8(Graphics2D g) { //Water
		
	}
	public void panel9(Graphics2D g) { //Water
		
	}
	public void panel10(Graphics2D g) { //Water
		
	}
	public void panel11(Graphics2D g) { //Water
		
	}
	public void panel12(Graphics2D g) { //Water
		
	}
	public void panel13(Graphics2D g) { //Water
		
	}
	

	public void setPanel(int p) {
		currentPanel = p;
	}
}