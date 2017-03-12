package gameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import tileMap.Background;
import main.GamePanel;
import main.Lang;

public class MenuState extends GameState {
	
	private Background bg;
	
	private static int currentChoice = 0;
	private static int lastChoice = 0;
	
	String[] options;
	
	private Font font;
	
	public MenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		Lang.init();
		currentChoice = lastChoice;
		
		options = new String[6];
		options[0] = Lang.resume;
		options[1] = Lang.newGame;
		options[2] = Lang.loadGame;
		options[3] = Lang.options;
		options[4] = Lang.credits;
		options[5] = Lang.quit;
		
		try {
			
			bg = new Background(1);
			bg.setVector(-0.5, 0);
			
			font = new Font("Boulder", Font.PLAIN, 46);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void init() {}
	
	public void update() {
		bg.update();
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
		bg.draw(g);
		
		// draw title
		g.drawImage(MenuImg.logo, GamePanel.WIDTH/2 - MenuImg.logo.getWidth(null)/2, 50, null);
		
		// draw menu options
		
		//Text antialiasing
		g.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		
		
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.ORANGE);
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawImage(MenuImg.button, GamePanel.WIDTH/2 - MenuImg.button.getWidth(null)/2, 250 + i * 80, null);
			g.drawString(options[i], (GamePanel.WIDTH/2 - MenuImg.button.getWidth(null)/2) + 30, 298 + i * 80);
		}
		
	}
	
	private void select() {
		if(currentChoice == 0) {
			lastChoice = currentChoice;
			gsm.setState(GameStateManager.LEVELSTATE);
		}
		if(currentChoice == 1) {
			lastChoice = currentChoice;
			gsm.setState(GameStateManager.LEVELSTATE);
		}
		if(currentChoice == 2) {
			lastChoice = currentChoice;
			gsm.setState(GameStateManager.LEVELSTATE);
		}
		if(currentChoice == 3) {
			lastChoice = currentChoice;
			gsm.setState(GameStateManager.OPTIONSSTATE);
		}
		if(currentChoice == 4) {
			lastChoice = currentChoice;
			gsm.setState(GameStateManager.CREDITSSTATE);
		}
		if(currentChoice == 5) {
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int k) {}
	
}