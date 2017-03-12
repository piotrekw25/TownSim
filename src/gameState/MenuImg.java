package gameState;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import main.GamePanel;

public class MenuImg {
	
	public static Image logo;
	public static Image button;
	public static Image box;
	
	public static BufferedImage menuBackground;
	public static BufferedImage menuBackgroundScaled;
	
	public static void LoadMenuImgs() {
		logo = new ImageIcon("Resources/UI/logo.png").getImage();
		button = new ImageIcon("Resources/UI/button.png").getImage();
		box = new ImageIcon("Resources/UI/box.png").getImage();
		
		try {
			menuBackground = ImageIO.read(new File("Resources/Backgrounds/bg.jpg"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		menuBackgroundScaled = resizeImage(menuBackground, 1, GamePanel.WIDTH, GamePanel.HEIGHT);
	}
	private static BufferedImage resizeImage(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT) {
	    BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	    Graphics2D g = resizedImage.createGraphics();
	    g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	    g.dispose();

	    return resizedImage;
	}
}