package View;

import Model.Result;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowGestionMatch extends JPanel {
	private TournamentFormatter formatter;
	private UserInteraction userInteraction;
	private JLabel title, player1, player2;
	private JPanel mainPanel, choicePanel, buttonPanel;
	private JComboBox<String> player1Box, player2Box;
	private JButton submit;

	public ShowGestionMatch() {
		formatter = new TournamentFormatter();
		userInteraction = new UserInteraction();

		this.setLayout(new BorderLayout());

		title = new JLabel("Gestion des matchs", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title, BorderLayout.NORTH);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		choicePanel = new JPanel();
		choicePanel.setLayout(new GridLayout(2, 2));
		String[] playerList = formatter.getPlayersList().toArray(new String[0]);

		player1 = new JLabel("Joueur 1 :");
		choicePanel.add(player1);
		player1Box = new JComboBox<>(playerList);
		player1Box.setFont(new Font("Arial", Font.PLAIN, 20));
		choicePanel.add(player1Box);

		player2 = new JLabel("Joueur 2 :");
		choicePanel.add(player2);
		player2Box = new JComboBox<>(playerList);
		player2Box.setFont(new Font("Arial", Font.PLAIN, 20));
		choicePanel.add(player2Box);

		mainPanel.add(choicePanel, BorderLayout.CENTER);

		buttonPanel = new JPanel();
		submit = new JButton("Valider");
		submit.addActionListener(new ButtonListener());
		submit.setFont(new Font("Arial", Font.PLAIN, 20));
		buttonPanel.add(submit);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		this.add(mainPanel, BorderLayout.CENTER);
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (player1Box.getSelectedItem() == player2Box.getSelectedItem()) {
				userInteraction.displayErrorMessage("Les joueurs doivent être différents");
			} else {
				// créer un nouveau match entre les deux joueurs
			}
		}
	}
}
