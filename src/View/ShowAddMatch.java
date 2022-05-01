package View;

import javax.swing.*;
import java.awt.*;

public class ShowAddMatch extends JPanel {
	private JPanel mainPanel;
	private MatchForm formPanel;
	private JLabel title;

	public ShowAddMatch() {
		this.setLayout(new BorderLayout());

		title = new JLabel("Ajout d'un match", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title, BorderLayout.NORTH);

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());

		formPanel = new MatchForm(true, null);
		mainPanel.add(formPanel);

		this.add(mainPanel, BorderLayout.CENTER);
	}
}
