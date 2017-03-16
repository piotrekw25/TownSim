package main;

import java.awt.Point;
import java.awt.event.*;

import gameState.GameStateManager;

public class Mouse implements MouseMotionListener, MouseListener {

	public void mouseClicked(MouseEvent e) {

		
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		GameStateManager.gameStates[GameStateManager.currentState].release(e.getButton());
	}

	public void mouseDragged(MouseEvent e) {
		GamePanel.mse = new Point((e.getX()) + ((GamePanel.WIDTH - GamePanel.WIDTH) / 2),
				(e.getY()) + ((GamePanel.HEIGHT - (GamePanel.HEIGHT)) - (GamePanel.WIDTH - GamePanel.WIDTH) / 2));
	}

	public void mouseMoved(MouseEvent e) {
		GamePanel.mse = new Point((e.getX()) - ((GamePanel.WIDTH - GamePanel.WIDTH) / 2),
				(e.getY()) - ((GamePanel.HEIGHT - (GamePanel.HEIGHT)) - (GamePanel.WIDTH - GamePanel.WIDTH) / 2));
		
		GameStateManager.gameStates[GameStateManager.currentState].entered();
		
		//System.out.println("X:" + e.getX() + " Y" + e.getY());
	}

}
