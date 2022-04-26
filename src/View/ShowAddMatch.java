package View;

import javax.swing.*;
import java.awt.*;

public class ShowAddMatch extends JPanel {
	private JPanel formPanel;
	private JLabel title;

	public ShowAddMatch() {
		this.setLayout(new BorderLayout());

		title = new JLabel("Ajout d'un match", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title, BorderLayout.NORTH);

		formPanel = new MatchForm(true, null);
		this.add(formPanel, BorderLayout.CENTER);
	}
}
