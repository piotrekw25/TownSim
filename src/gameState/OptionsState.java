package gameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import tileMap.Background;
import main.GamePanel;
import main.Lang;

public class OptionsState extends GameState {

	private Background bg;

	private static int currentChoice = 0;
	private static int currentChoiceButton = 0; // horizontal choice

	private static int numOfChoices = 2;
	public Rectangle[][] button;
	String[] options;

	private Font font;

	public OptionsState(GameStateManager gsm) {

		this.gsm = gsm;

		options = new String[numOfChoices];
		options[0] = Lang.getCurrentLanguage(Lang.currentLanguage);
		options[1] = Lang.back;

		button = new Rectangle[numOfChoices][3];

		// Language button
		button[0][0] = new Rectangle((GamePanel.WIDTH / 2 - MenuImg.button2.getWidth(null) / 2) + 64, 250 + 160, 256,
				MenuImg.button.getHeight(null)); // button
		button[0][1] = new Rectangle(GamePanel.WIDTH / 2 - MenuImg.button2.getWidth(null) / 2, 250 + 160, 64,
				MenuImg.button.getHeight(null)); // left arrow
		button[0][2] = new Rectangle((GamePanel.WIDTH / 2) + (MenuImg.button2.getWidth(null) / 2) - 64, 250 + 160, 64,
				MenuImg.button.getHeight(null)); // right arrow

		// Back button
		button[1][0] = new Rectangle(GamePanel.WIDTH / 2 - MenuImg.button.getWidth(null) / 2, 250 + 350 + 1 * 80,
				MenuImg.button.getWidth(null), MenuImg.button.getHeight(null));

		try {

			bg = new Background(1);
			bg.setVector(-0.5, 0);

			font = new Font("Boulder", Font.PLAIN, 46);

		} catch (Exception e) {
			e.printStackTrace();
		}

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

		// Text antialiasing
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

		// Options title
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawImage(MenuImg.button, GamePanel.WIDTH / 2 - MenuImg.button.getWidth(null) / 2, 250, null);
		g.drawString(Lang.options, (GamePanel.WIDTH / 2 - MenuImg.button.getWidth(null) / 2) + 30, 298);

		// Box
		g.drawImage(MenuImg.box, GamePanel.WIDTH / 2 - MenuImg.box.getWidth(null) / 2, 250 + 80, null);

		// Language text
		g.drawString(Lang.language + ":", GamePanel.WIDTH / 2 - MenuImg.box.getWidth(null) / 2 + 40, 250 + 140);

		// draw menu options
		g.fillRect(GamePanel.WIDTH / 2 - MenuImg.button2.getWidth(null) / 2 + 64, 250 + 160, 256,
				MenuImg.button.getHeight(null));
		// Buttons
		if (0 == currentChoice) {
			g.setColor(Color.ORANGE);
		} else {
			g.setColor(Color.BLACK);
		}
		g.drawImage(MenuImg.button2, GamePanel.WIDTH / 2 - MenuImg.button2.getWidth(null) / 2, 250 + 160, null);

		options[0] = Lang.getCurrentLanguage(currentChoiceButton);
		int width = g.getFontMetrics().stringWidth(Lang.getCurrentLanguage(currentChoiceButton)); // string
																									// length
																									// in
																									// pixels
		g.drawString(options[0], GamePanel.WIDTH / 2 - (width / 2), 250 + 208);

		// Back
		if (1 == currentChoice) {
			g.setColor(Color.ORANGE);
		} else {
			g.setColor(Color.BLACK);
		}
		g.drawImage(MenuImg.button, GamePanel.WIDTH / 2 - MenuImg.button.getWidth(null) / 2, 250 + 350 + 1 * 80, null);
		g.drawString(options[1], (GamePanel.WIDTH / 2 - MenuImg.button.getWidth(null) / 2) + 30, 298 + 350 + 1 * 80);
	}

	private void select() {
		if (currentChoice == 0) {
			Lang.changeLanguage(currentChoiceButton);
			options[0] = Lang.language;
			options[1] = Lang.back;
		}
		if (currentChoice == 1) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	public void keyReleased(int k) {
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
		if (k == KeyEvent.VK_LEFT) {
			currentChoiceButton--;
			if (currentChoiceButton == -1) {
				currentChoiceButton = Lang.Languages - 1;
			}
			select();
		}
		if (k == KeyEvent.VK_RIGHT) {
			currentChoiceButton++;
			if (currentChoiceButton == Lang.Languages) {
				currentChoiceButton = 0;
			}
			select();
		}
	}

	public void release(int mouseButton) {
		if (mouseButton == 1) {
			for (int i = 0; i < button.length; i++) {
				if (button[i][0].contains(GamePanel.mse)) {
					currentChoice = i;
					select();
				}
			}
			if (button[0][1].contains(GamePanel.mse)) {
				currentChoiceButton--;
				if (currentChoiceButton == -1)
					currentChoiceButton = Lang.Languages - 1;
				select();
			}
			if (button[0][2].contains(GamePanel.mse)) {
				currentChoiceButton++;
				if (currentChoiceButton == Lang.Languages)
					currentChoiceButton = 0;
				select();
			}
		}
		MenuState.click1.play();
	}

	public void entered() {
		for (int i = 0; i < button.length; i++) {
			if (button[i][0].contains(GamePanel.mse)) {
				currentChoice = i;
			}
		}
		if (button[0][1].contains(GamePanel.mse))
			currentChoice = 0;
		if (button[0][2].contains(GamePanel.mse))
			currentChoice = 0;
	}
}