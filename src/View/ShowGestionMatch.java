package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowGestionMatch extends JPanel {
	private TournamentFormatter formatter;
	private UserInteraction userInteraction;
	private static MatchAnimationWindow matchAnimationWindow;
	private JLabel title, match, player1, player2;
	private JPanel mainPanel, choicePanel, playersPanel, buttonPanel;
	private JComboBox<String> matchBox, player1Box, player2Box;
	private DefaultComboBoxModel matchBoxModel;
	private JButton submit;
	private Border border, margin;

	public ShowGestionMatch() {
		formatter = new TournamentFormatter();
		userInteraction = new UserInteraction();

		this.setLayout(new BorderLayout());

		title = new JLabel("Gestion des matchs", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title, BorderLayout.NORTH);

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());

		choicePanel = new JPanel();
		choicePanel.setLayout(new BorderLayout());
		choicePanel.setBackground(new Color(255,250,205));

		playersPanel = new JPanel();
		playersPanel.setLayout(new GridLayout(3, 2, 10, 10));
		playersPanel.setOpaque(false);

		match = new JLabel("Match :", SwingConstants.RIGHT);
		match.setFont(new Font("Arial", Font.PLAIN, 20));
		playersPanel.add(match);
		matchBoxModel = new DefaultComboBoxModel(formatter.getMatchsWithoutResultList().toArray(new String[0]));
		matchBox = new JComboBox<>(matchBoxModel);
		matchBox.setFont(new Font("Arial", Font.PLAIN, 20));
		playersPanel.add(matchBox);

		String[] playerList = formatter.getPlayersList().toArray(new String[0]);
		player1 = new JLabel("Joueur 1 :", SwingConstants.RIGHT);
		player1.setFont(new Font("Arial", Font.PLAIN, 20));
		playersPanel.add(player1);
		player1Box = new JComboBox<>(playerList);
		player1Box.setFont(new Font("Arial", Font.PLAIN, 20));
		playersPanel.add(player1Box);

		player2 = new JLabel("Joueur 2 :", SwingConstants.RIGHT);
		player2.setFont(new Font("Arial", Font.PLAIN, 20));
		playersPanel.add(player2);
		player2Box = new JComboBox<>(playerList);
		player2Box.setFont(new Font("Arial", Font.PLAIN, 20));
		playersPanel.add(player2Box);

		choicePanel.add(playersPanel, BorderLayout.CENTER);

		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		submit = new JButton("Valider");
		submit.addActionListener(new ButtonListener());
		submit.setFont(new Font("Arial", Font.PLAIN, 20));
		buttonPanel.add(submit);
		choicePanel.add(buttonPanel, BorderLayout.SOUTH);

		border = BorderFactory.createRaisedBevelBorder();
		margin = new EmptyBorder(10,10,10,10);
		choicePanel.setBorder(new CompoundBorder(border, margin));

		mainPanel.add(choicePanel);

		this.add(mainPanel, BorderLayout.CENTER);
	}

	public void updateMatchs() {
		matchBox.setSelectedIndex(0);
		matchBoxModel = new DefaultComboBoxModel(formatter.getMatchsWithoutResultList().toArray(new String[0]));
		matchBox.setModel(matchBoxModel);
	}

	/**
	 * Remove the Matchs animations window
	 */
	public void removeMatchAnimationWindow() {
		this.matchAnimationWindow = null;
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (player1Box.getSelectedItem() == player2Box.getSelectedItem()) {
				userInteraction.displayErrorMessage("Les joueurs doivent ??tre diff??rents");
			} else if (matchAnimationWindow != null && matchAnimationWindow.hasMatch(matchBox.getSelectedItem().toString())) {
				userInteraction.displayErrorMessage("Ce match est d??j?? en cours");
			} else {
				if (matchAnimationWindow == null) {
					matchAnimationWindow = new MatchAnimationWindow(ShowGestionMatch.this);
				}
				matchAnimationWindow.addMatch(
					matchBox.getSelectedItem().toString(),
					player1Box.getSelectedItem().toString(),
					player2Box.getSelectedItem().toString()
				);
			}
		}
	}
}
