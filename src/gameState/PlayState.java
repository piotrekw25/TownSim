package gameState;

import main.GamePanel;
import main.Mouse;
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

	// private ArrayList<Enemy> enemies;
	// private ArrayList<Explosion> explosions;

	private HUD hud;

	// private AudioPlayer bgMusic;

	private int tileSize = 64;
	private boolean selectedRoadAngle; // true 2 (up down road) false 3 (left
										// right road)
	private long start = System.currentTimeMillis();
	public static long elapsed;

	public static int road;
	public static int currCol;
	public static int currRow;

	public static int selection = 0;

	public static int buildRoads = 0;
	public static int buildResidential = 1;
	public static int buildCommercial = 2;
	public static int buildIndustrial = 3;
	public static int buildElectricity = 4;
	public static int buildWater = 5;

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

		// bg = new Background("/Backgrounds/grassbg1.gif", 0.1);

		camera = new Camera(tileMap);
		camera.setPosition(64, 64);

		hud = new HUD(camera);

		// bgMusic = new AudioPlayer("/Music/level1-1.mp3");
		// bgMusic.play();

	}

	public void update() {

		// update player
		camera.update();

		tileMap.setPosition(GamePanel.WIDTH / 2 - camera.getx(), GamePanel.HEIGHT / 2 - camera.gety());

		// taxes and maintenace

		elapsed = System.currentTimeMillis() - start;
		if (elapsed >= Values.dayLength) {
			Values.day++;
			start = System.currentTimeMillis();

			// Available homes
			Values.homes += Values.residentialCount * Values.citizensPerResidential;

			// Calculate number of available jobs
			Values.availableJobs = (Values.commercialJobs * Values.commercialCount)
					+ (Values.industrialJobs * Values.industrialCount) + (Values.waterPumpJobs * Values.waterPumpCount)
					+ (Values.powerPlantJobs * Values.powerPlantCount);

			// Power and water output
			Values.electricity = Values.powerPlantCount * Values.powerPlantOutput;
			Values.water = Values.waterPumpCount * Values.waterPumpOutput;

			// Electricity need
			Values.electricityUsed = Values.population * Values.citizenElectricityRequirment
					+ Values.commercialCount * Values.commercialElectricityRequirment
					+ Values.industrialCount * Values.industrialElectricityRequirment
					+ Values.waterPumpCount * Values.waterPumpElectricityRequirment;

			// Water need
			Values.waterUsed = Values.population * Values.citizenWaterRequirment
					+ Values.commercialCount * Values.commercialWaterRequirment
					+ Values.industrialCount * Values.industrialWaterRequirment
					+ Values.powerPlantCount * Values.powerPlantWaterRequirment;

			// maintence
			Values.dailyMaintenace = Values.roadCount * Values.roadMaintenace
					+ Values.waterPumpCount * Values.waterPumpMaintenace
					+ Values.powerPlantCount * Values.powerPlantMaintenace;

			Values.money -= Values.dailyMaintenace;

			// taxes

			if (Values.waterUsed < Values.water && Values.electricityUsed < Values.electricity) {
				Values.dailyTaxes = Values.population * Values.taxPerCitizen
						+ Values.commercialTax * Values.commercialCount + 
						Values.industrialTax * Values.industrialCount;
				Values.money += Values.dailyTaxes;
			}

			// Populate with citizens
			populate();

		}

	}

	public void populate() {

		if (Values.population < Values.homes) {
			if (Values.population < Values.availableJobs) {
				if (Values.homes - Values.population >= 4) {
					Values.population += 4;
				} else if (Values.homes - Values.population == 3) {
					Values.population += 3;
				} else if (Values.homes - Values.population == 2) {
					Values.population += 2;
				} else if (Values.homes - Values.population == 1) {
					Values.population += 1;
				}
			}
		}

	}

	public void draw(Graphics2D g) {

		// draw tilemap
		tileMap.draw(g);

		hud.draw(g);

	}

	public void keyPressed(int k) {
//		if (k == KeyEvent.VK_A)
//			camera.setLeft(true);
//		if (k == KeyEvent.VK_D)
//			camera.setRight(true);
//		if (k == KeyEvent.VK_W)
//			camera.setUp(true);
//		if (k == KeyEvent.VK_S)
//			camera.setDown(true);

		// if (k == KeyEvent.VK_1)
		// hud.setPanel(hud.roadsPanel);
		// if (k == KeyEvent.VK_2)
		// hud.setPanel(hud.zonesPanel);
		// if (k == KeyEvent.VK_3)
		// hud.setPanel(hud.electricityPanel);
		// if (k == KeyEvent.VK_4)
		// hud.setPanel(hud.waterPanel);

		if (k == KeyEvent.VK_1)
			selection = buildRoads;
		if (k == KeyEvent.VK_2)
			selection = buildResidential;
		if (k == KeyEvent.VK_3)
			selection = buildCommercial;
		if (k == KeyEvent.VK_4)
			selection = buildIndustrial;
		if (k == KeyEvent.VK_5)
			selection = buildElectricity;
		if (k == KeyEvent.VK_6)
			selection = buildWater;

	}

	public void keyReleased(int k) {
//		if (k == KeyEvent.VK_A)
//			camera.setLeft(false);
//		if (k == KeyEvent.VK_D)
//			camera.setRight(false);
//		if (k == KeyEvent.VK_W)
//			camera.setUp(false);
//		if (k == KeyEvent.VK_S)
//			camera.setDown(false);

		if (k == KeyEvent.VK_R)
			selectedRoadAngle = !selectedRoadAngle;

	}

	public boolean checkIfRoad() {
		if (tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] > 1
				&& tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] < 13) {
			return true;
		}
		return false;

	}

	public boolean bordersWithRoad() {

		if (tileMap.map[currRow + tileMap.rowOffset+1][currCol + tileMap.colOffset] > 1 &&
				tileMap.map[currRow + tileMap.rowOffset+1][currCol + tileMap.colOffset] < 13
				
				|| tileMap.map[currRow + tileMap.rowOffset-1][currCol + tileMap.colOffset] > 1 &&
				tileMap.map[currRow + tileMap.rowOffset-1][currCol + tileMap.colOffset] < 13
				
				|| tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset+1] > 1 &&
				tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset+1] < 13
				
				|| tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset-1] > 1 &&
				tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset-1] < 13
				
				
				
				
				) {
			return true;
		}
		return false;
	}

	public void roadMoneyBack() {
		if (checkIfRoad()) {
			Values.money += Values.roadCost;
			Values.roadCount--;
		}
	}

	public void press(int mouseButton) {
		currCol = (int) Mouse.mouseX / tileSize;
		currRow = (int) Mouse.mouseY / tileSize;

		if (mouseButton == 3) {

			if(tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] > 1 &&
				tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] < 13) {
				roadMoneyBack();
				tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 1;
				
				}else if(tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] == 13){
					Values.money += Values.residentialCost;
					Values.residentialCount--;
					tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 1;
					
				}else if(tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] == 14){
					Values.money += Values.commercialCost;
					Values.commercialCount--;
					tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 1;
					
				}else if(tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] == 15){
					Values.money += Values.industrialCost;
					Values.industrialCount--;
					tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 1;
					
				}else if(tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] == 16){
					Values.money += Values.waterPumpCost;
					Values.waterPumpCount--;
					tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 1;
				}else if(tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] == 16){
					Values.money += Values.powerPlantCost;
					Values.powerPlantCount--;
					tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 1;
				}
			
			
			
			
			
			

		} else if (mouseButton == 1) {
			if (selection == buildRoads) {
				buildRoads();
			}
			if (selection == buildResidential) {
				buildResidential();
			}
			if (selection == buildCommercial) {
				buildCommercial();
			}
			if (selection == buildIndustrial) {
				buildIndustrial();
			}
			if (selection == buildElectricity) {
				buildElectricity();
			}
			if (selection == buildWater) {
				buildWater();
			}

		}
	}

	public void entered() {

	}

	public void buildRoads() {
		if (selectedRoadAngle) {
			road = 2;
		} else {
			road = 3;
		}

		// skrzyzowanie 4 dróg
		if (tileMap.map[currRow + tileMap.rowOffset + 1][currCol + tileMap.colOffset] == 2
				&& tileMap.map[currRow + tileMap.rowOffset - 1][currCol + tileMap.colOffset] == 2
				&& tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset + 1] == 3
				&& tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset - 1] == 3) {
			roadMoneyBack();
			tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 4;

		} else if (tileMap.map[currRow + tileMap.rowOffset - 1][currCol + tileMap.colOffset] == 2
				&& tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset + 1] == 3
				&& tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset - 1] == 3) {
			roadMoneyBack();
			tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 5;

		} else if (tileMap.map[currRow + tileMap.rowOffset + 1][currCol + tileMap.colOffset] == 2
				&& tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset + 1] == 3
				&& tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset - 1] == 3) {
			roadMoneyBack();
			tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 7;

		} else if (tileMap.map[currRow + tileMap.rowOffset + 1][currCol + tileMap.colOffset] == 2
				&& tileMap.map[currRow + tileMap.rowOffset - 1][currCol + tileMap.colOffset] == 2
				&& tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset + 1] == 3) {
			roadMoneyBack();
			tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 6;

		} else if (tileMap.map[currRow + tileMap.rowOffset + 1][currCol + tileMap.colOffset] == 2
				&& tileMap.map[currRow + tileMap.rowOffset - 1][currCol + tileMap.colOffset] == 2
				&& tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset - 1] == 3) {
			roadMoneyBack();
			tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 8;

		} else if (tileMap.map[currRow + tileMap.rowOffset + 1][currCol + tileMap.colOffset] == 2
				&& tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset + 1] == 3) {
			roadMoneyBack();
			tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 11;

		} else if (tileMap.map[currRow + tileMap.rowOffset - 1][currCol + tileMap.colOffset] == 2
				&& tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset - 1] == 3) {
			roadMoneyBack();
			tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 9;

		} else if (tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset - 1] == 3
				&& tileMap.map[currRow + tileMap.rowOffset + 1][currCol + tileMap.colOffset] == 2) {
			roadMoneyBack();
			tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 12;

		} else if (tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset + 1] == 3
				&& tileMap.map[currRow + tileMap.rowOffset - 1][currCol + tileMap.colOffset] == 2) {
			roadMoneyBack();
			tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 10;
		} else {
			roadMoneyBack();
			tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = road;
		}

		Values.money -= Values.roadCost;
		Values.roadCount++;
	}

	public boolean checkIfGrass() {
		if (tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] == 1) {
			return true;
		}
		return false;
	}

	public void buildResidential() {

		if (!checkIfRoad()) {
			if (bordersWithRoad()) {
				if (checkIfGrass()) {
					tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 13;

					Values.money -= Values.residentialCost;
					Values.residentialCount++;
				}

			}
		}
	}

	public void buildCommercial() {

		if (!checkIfRoad()) {
			if (bordersWithRoad()) {
				if (checkIfGrass()) {
					tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 14;

					Values.money -= Values.commercialCost;
					Values.commercialCount++;
				}

			}
		}
	}

	public void buildIndustrial() {

		if (!checkIfRoad()) {
			if (bordersWithRoad()) {
				if (checkIfGrass()) {
					tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 15;

					Values.money -= Values.industrialCost;
					Values.industrialCount++;
				}

			}
		}
	}

	public void buildElectricity() {

		if (!checkIfRoad()) {
			if (bordersWithRoad()) {
				if (checkIfGrass()) {
					tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 16;

					Values.money -= Values.powerPlantCost;
					Values.powerPlantCount++;
				}

			}
		}

	}

	public void buildWater() {

		if (!checkIfRoad()) {
			if (bordersWithRoad()) {
				if (checkIfGrass()) {
					tileMap.map[currRow + tileMap.rowOffset][currCol + tileMap.colOffset] = 17;

					Values.money -= Values.waterPumpCost;
					Values.waterPumpCount++;
				}

			}
		}

	}

}