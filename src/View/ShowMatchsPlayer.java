package View;

import Business.ManagerUtils;
import Business.TournamentManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowMatchsPlayer extends JPanel {
	private TournamentManagement manager;
	private JPanel playerPanel;
	private JLabel title;
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox<String> playerBox;
	private JButton submit;

	public ShowMatchsPlayer() {
		manager = new TournamentManagement();
		this.setLayout(new BorderLayout());

		// title
		title = new JLabel("Matchs d'un joueur", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title, BorderLayout.NORTH);

		// table
		String[] tableHead = {"Tournoi", "Date de début", "Emplacement", "Arbitre", "Points"};
		tableModel = new DefaultTableModel(tableHead, 0);
		table = new JTable(tableModel);
		table.setRowHeight(30);
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		this.add(new JScrollPane(table), BorderLayout.CENTER);

		playerPanel = new JPanel();

		playerBox = new JComboBox<>(manager.getPlayersList().toArray(new String[0]));
		playerPanel.add(playerBox);
		submit = new JButton("Submit");
		submit.addActionListener(new ButtonListener());
		playerPanel.add(submit);
		this.add(playerPanel, BorderLayout.SOUTH);
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			tableModel.setRowCount(0);
			int playerID = ManagerUtils.getIDFromDescription(playerBox.getSelectedItem().toString());
			for (String[] match : manager.getMatchsPlayer(playerID)) {
				tableModel.addRow(match);
			}
		}
	}
}
