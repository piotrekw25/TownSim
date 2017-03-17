package gameState;

import main.GamePanel;
import main.Lang;

import java.awt.*;

public class LoadingState extends GameState {

	public LoadingState(GameStateManager gsm) {

		Lang.init();
		MenuImg.LoadMenuImgs();
		gsm.setState(GameStateManager.MENUSTATE);

	}

	public void init() {
	}

	public void update() {
	}

	public void draw(Graphics2D g) {

		// draw bg
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		// Text antialiasing
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

		// Loading text
		g.setFont(new Font("Boulder", Font.PLAIN, 120));
		int width = g.getFontMetrics().stringWidth(Lang.loading);
		g.setColor(Color.BLACK);
		g.drawString(Lang.loading, GamePanel.WIDTH / 2 - (width / 2), GamePanel.HEIGHT / 2);

	}

	public void keyPressed(int k) {
	}

	public void keyReleased(int k) {
	}

}