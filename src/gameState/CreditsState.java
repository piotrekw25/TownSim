package gameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import tileMap.Background;
import main.GamePanel;
import main.Lang;

public class CreditsState extends GameState {
	
	private Background bg;
	
	private int currentChoice = 0;
	
	String[] options;
	
	private Font font;
	
	public CreditsState(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		Lang.init();
		
		options = new String[1];
		options[0] = Lang.back;
		
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
		
		//Credits title
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawImage(MenuImg.button, GamePanel.WIDTH/2 - MenuImg.button.getWidth(null)/2, 250, null);
		g.drawString(Lang.credits, (GamePanel.WIDTH/2 - MenuImg.button.getWidth(null)/2) + 30, 298);
		
		//Box
		g.drawImage(MenuImg.box, GamePanel.WIDTH/2 - MenuImg.box.getWidth(null)/2, 250 + 80, null);
		g.drawString("Piotr Weber", (GamePanel.WIDTH/2 - MenuImg.button.getWidth(null)/2) + 30, 298 + 120);
		g.drawString("Adam Gawliñski", (GamePanel.WIDTH/2 - MenuImg.button.getWidth(null)/2) + 30, 298 + 2* 90);
		
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.ORANGE);
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawImage(MenuImg.button, GamePanel.WIDTH/2 - MenuImg.button.getWidth(null)/2, 250 + 350, null);
			g.drawString(options[0], (GamePanel.WIDTH/2 - MenuImg.button.getWidth(null)/2) + 30, 298 + 350);
		}
		
	}
	
	private void select() {
		if(currentChoice == 0) {
			gsm.setState(GameStateManager.MENUSTATE);
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