package gameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import audio.AudioPlayer;
import tileMap.Background;
import main.GamePanel;
import main.Lang;

public class MenuState extends GameState {

	private Background bg;

	private static int currentChoice = 0;
	private static int lastChoice = 0;

	private static int numOfChoices = 6;
	public Rectangle[] button;
	String[] options;

	private Font font;
	
	public AudioPlayer bgMusic;
	
	public static AudioPlayer click1;

	public static boolean musicAlreadyPlaying = false;

	public MenuState(GameStateManager gsm) {

		this.gsm = gsm;
		currentChoice = lastChoice;

		options = new String[numOfChoices];
		options[0] = Lang.resume;
		options[1] = Lang.newGame;
		options[2] = Lang.loadGame;
		options[3] = Lang.options;
		options[4] = Lang.credits;
		options[5] = Lang.quit;

		button = new Rectangle[numOfChoices];

		for (int i = 0; i < options.length; i++) {
			button[i] = new Rectangle(GamePanel.WIDTH / 2 - MenuImg.button.getWidth(null) / 2, 250 + i * 80,
					MenuImg.button.getWidth(null), MenuImg.button.getHeight(null));
		}

		try {

			bg = new Background(1);
			bg.setVector(-0.5, 0);

			font = new Font("Boulder", Font.PLAIN, 46);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!musicAlreadyPlaying) {
			bgMusic = new AudioPlayer("/music/no-more-no-less_GJCvJUr_.mp3");
			bgMusic.playLoop();
			musicAlreadyPlaying = true;
		}
		
		click1 = new AudioPlayer("/sound/click1.mp3");
		

	}

	public void init() {
	}

	public void update() {
		bg.update();
	}

	public void draw(Graphics2D g) {

		// draw bg
		bg.draw(g);

		// draw title
		g.drawImage(MenuImg.logo, GamePanel.WIDTH / 2 - MenuImg.logo.getWidth(null) / 2, 50, null);

		// draw menu options

		// Text antialiasing
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.ORANGE);
			} else {
				g.setColor(Color.BLACK);
			}

			g.drawImage(MenuImg.button, GamePanel.WIDTH / 2 - MenuImg.button.getWidth(null) / 2, 250 + i * 80, null);
			g.drawString(options[i], (GamePanel.WIDTH / 2 - MenuImg.button.getWidth(null) / 2) + 30, 298 + i * 80);
		}

	}

	private void select() {
		if (currentChoice == 0) {
			lastChoice = currentChoice;
			gsm.setState(GameStateManager.LEVELSTATE);
		}
		if (currentChoice == 1) {
			lastChoice = currentChoice;
			gsm.setState(GameStateManager.PLAYSTATE);
		}
		if (currentChoice == 2) {
			lastChoice = currentChoice;
			gsm.setState(GameStateManager.LEVELSTATE);
		}
		if (currentChoice == 3) {
			lastChoice = currentChoice;
			gsm.setState(GameStateManager.OPTIONSSTATE);
		}
		if (currentChoice == 4) {
			lastChoice = currentChoice;
			gsm.setState(GameStateManager.CREDITSSTATE);
		}
		if (currentChoice == 5) {
			System.exit(0);
		}
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		if (k == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
		click1.play();
	}

	public void keyReleased(int k) {

	}

	public void release(int mouseButton) {
		if (mouseButton == 1) {
			for (int i = 0; i < button.length; i++) {
				if (button[i].contains(GamePanel.mse)) {
					currentChoice = i;
					select();
				}
			}
		}
	}

	public void entered() {
		for (int i = 0; i < button.length; i++) {
			if (button[i].contains(GamePanel.mse)) {
				currentChoice = i;

			}
		}
	}
}