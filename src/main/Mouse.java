package main;

import java.awt.Point;
import java.awt.event.*;

import audio.AudioPlayer;
import gameState.GameStateManager;

public class Mouse implements MouseMotionListener, MouseListener {

	public static int mouseX;
	public static int mouseY;
	public AudioPlayer click1 = new AudioPlayer("/sound/click1.mp3");
	
	
	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		GameStateManager.gameStates[GameStateManager.currentState].press(e.getButton());
	}

	public void mouseReleased(MouseEvent e) {
		GameStateManager.gameStates[GameStateManager.currentState].release(e.getButton());
		click1.play();
	}

	public void mouseDragged(MouseEvent e) {
		GamePanel.mse = new Point((e.getX()) + ((GamePanel.WIDTH - GamePanel.WIDTH) / 2),
				(e.getY()) + ((GamePanel.HEIGHT - (GamePanel.HEIGHT)) - (GamePanel.WIDTH - GamePanel.WIDTH) / 2));
	}

	public void mouseMoved(MouseEvent e) {
		GamePanel.mse = new Point((e.getX()) - ((GamePanel.WIDTH - GamePanel.WIDTH) / 2),
				(e.getY()) - ((GamePanel.HEIGHT - (GamePanel.HEIGHT)) - (GamePanel.WIDTH - GamePanel.WIDTH) / 2));

		GameStateManager.gameStates[GameStateManager.currentState].entered();
		
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
//	public static

}
