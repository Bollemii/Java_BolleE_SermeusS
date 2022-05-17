package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShowWelcome extends JPanel {
	private UserInteraction userInteraction;
	private JLabel welcome;
	private BufferedImage image;

	public ShowWelcome() {
		userInteraction = new UserInteraction();

		this.setLayout(new BorderLayout());

		welcome = new JLabel("Bienvenue dans l'application de gestion de tournois", SwingConstants.CENTER);
		welcome.setFont(new Font("Arial", Font.PLAIN, 25));
		this.add(welcome, BorderLayout.CENTER);
		try {
			image = ImageIO.read(new File("pictures/Podium.png"));
		} catch (IOException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (image != null)
			g.drawImage(image, this.getWidth()/2 - image.getWidth()/2, this.getHeight()/2 - image.getHeight() + 10, null);
	}
}
