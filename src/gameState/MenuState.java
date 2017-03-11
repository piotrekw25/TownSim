package gameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import tileMap.Background;
import main.Lang;

public class MenuState extends GameState {
	
	private Background bg;
	
	private int currentChoice = 0;
	
	String[] options;
	
	private Font font;

	private Image logo;
	private Image button;
	
	public MenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		Lang.init();
		
		logo = new ImageIcon("Resources/logo.png").getImage();
		button = new ImageIcon("Resources/button.png").getImage();
		
		options = new String[6];
		options[0] = Lang.resume;
		options[1] = Lang.newGame;
		options[2] = Lang.loadGame;
		options[3] = Lang.options;
		options[4] = Lang.credits;
		options[5] = Lang.quit;
		
		try {
			
			bg = new Background("/Backgrounds/bg.jpg", 1);
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
		g.drawImage(logo, 50, 50, null);
		
		// draw menu options
		
		
		
		
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.ORANGE);
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawImage(button, 140, 250 + i * 80, null);
			g.drawString(options[i], 170, 298 + i * 80);
		}
		
	}
	
	private void select() {
		if(currentChoice == 0) {
			gsm.setState(GameStateManager.LEVELSTATE);
		}
		if(currentChoice == 1) {
			gsm.setState(GameStateManager.LEVELSTATE);
		}
		if(currentChoice == 2) {
			gsm.setState(GameStateManager.LEVELSTATE);
		}
		if(currentChoice == 3) {
			gsm.setState(GameStateManager.OPTIONSSTATE);
		}
		if(currentChoice == 4) {
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