package View;

import View.TableModels.AllPlayersModel;

import javax.swing.*;
import java.awt.*;

public class ShowAllPlayers extends JPanel {
	private TournamentFormatter formatter;
	private JTable table;
	private JLabel title;

	public ShowAllPlayers() {
		formatter = new TournamentFormatter();
		this.setLayout(new BorderLayout());

		title = new JLabel("Tableau des joueurs", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title, BorderLayout.NORTH);

		table = new JTable(new AllPlayersModel(formatter.getAllPlayers()));
		table.setRowHeight(30);
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		this.add(new JScrollPane(table), BorderLayout.CENTER);
	}
}