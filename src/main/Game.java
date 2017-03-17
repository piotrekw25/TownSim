package main;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {

		JFrame window = new JFrame("TownSim");
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setUndecorated(true);
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);

		window.addMouseListener(new Mouse());
		window.addMouseMotionListener(new Mouse());

	}

}
