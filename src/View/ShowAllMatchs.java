package View;

import View.TableModels.AllMatchsModel;

import javax.swing.*;
import java.awt.*;

public class ShowAllMatchs extends JPanel {
	private TournamentFormatter formatter;
	private JTable table;
	private JLabel title;

	public ShowAllMatchs() {
		formatter = new TournamentFormatter();
		setLayout(new BorderLayout());

		title = new JLabel("Tableau des matchs", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		add(title, BorderLayout.NORTH);

		table = new JTable(new AllMatchsModel(formatter.getAllMatchs()));
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);

		table.setRowHeight(30);
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
}
