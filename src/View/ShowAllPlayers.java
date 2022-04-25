package View;

import Business.TournamentManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShowAllPlayers extends JPanel {
	private TournamentManagement manager;
	private JTable table;
	private JLabel title;

	public ShowAllPlayers() {
		manager = new TournamentManagement();
		setLayout(new BorderLayout());

		// title
		title = new JLabel("Tableau des joueurs", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		add(title, BorderLayout.NORTH);

		// table
		String[] tableHead = {"Id", "Prénom", "Nom", "Date de naissance", "Genre", "Professionel", "Elo"};
		DefaultTableModel tableModel = new DefaultTableModel(tableHead, 0);
		table = new JTable(tableModel);

		for (String[] match : manager.getAllPlayers()) {
			tableModel.addRow(match);
		}
		table.setRowHeight(30);
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
}