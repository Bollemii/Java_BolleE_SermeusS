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
	private JPanel tournamentPanel;
	private JLabel title;
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox<String> tournamentBox;
	private JButton submit;

	//constructor
	public ShowMatchsPlayer() {
		manager = new TournamentManagement();

		manager = new TournamentManagement();
		setLayout(new BorderLayout());

		title = new JLabel("Matchs d'un joueur", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		add(title, BorderLayout.NORTH);


		String[] tableHead = {"Match", "Place", "Cout"};
		tableModel = new DefaultTableModel(tableHead, 0);
		table = new JTable(tableModel);
		table.setRowHeight(30);
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		add(new JScrollPane(table), BorderLayout.CENTER);

		tournamentPanel = new JPanel();
		tournamentBox = new JComboBox<>(manager.getTournamentsList().toArray(new String[0]));
		tournamentPanel.add(tournamentBox);
		submit = new JButton("Submit");
		submit.addActionListener(new ButtonListener());

		tournamentPanel.add(submit);
		add(tournamentPanel, BorderLayout.SOUTH);

	}
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//System.out.println(manager.getAllMatchsTournament(ManagerUtils.getIDFromDescription(tournamentList.getSelectedValue().toString())));
			tableModel.setRowCount(0);
			int tournamentId = ManagerUtils.getIDFromDescription(tournamentBox.getSelectedItem().toString());
			for (String[] match : manager.getMatchsTournament(tournamentId)) {
				tableModel.addRow(match);
			}
			System.out.println(tableModel.getRowCount());
			ShowMatchsPlayer.this.repaint();
		}
	}
}
