package View;

import View.Forms.PersonForm;

import javax.swing.*;
import java.awt.*;

public class ShowNewPerson extends JPanel {
	private JPanel mainPanel;
	private PersonForm formPanel;
	private JLabel title;

	public ShowNewPerson() {
		this.setLayout(new BorderLayout());

		title = new JLabel("Nouvelle personne", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title, BorderLayout.NORTH);

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());

		formPanel = new PersonForm();
		mainPanel.add(formPanel);

		this.add(mainPanel, BorderLayout.CENTER);
	}
}
