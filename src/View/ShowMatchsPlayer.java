package View;

import Business.ManagerUtils;
import View.TableModels.MatchsPlayerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowMatchsPlayer extends JPanel {
	private TournamentFormatter formatter;
	private JPanel playerPanel;
	private JLabel title;
	private JTable table;
	private MatchsPlayerModel model;
	private JComboBox<String> playerBox;
	private JButton submit;

	public ShowMatchsPlayer() {
		formatter = new TournamentFormatter();
		this.setLayout(new BorderLayout());

		title = new JLabel("Matchs d'un joueur", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title, BorderLayout.NORTH);

		model = new MatchsPlayerModel();
		table = new JTable(model);
		table.setRowHeight(30);
		table.setFont(new Font("Arial", Font.PLAIN, 15));
		this.add(new JScrollPane(table), BorderLayout.CENTER);

		playerPanel = new JPanel();

		playerBox = new JComboBox<>(formatter.getPlayersList().toArray(new String[0]));
		playerPanel.add(playerBox);
		submit = new JButton("Valider");
		submit.addActionListener(new ButtonListener());
		playerPanel.add(submit);
		this.add(playerPanel, BorderLayout.SOUTH);
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.clear();
			int playerID = ManagerUtils.getIDFromDescription(playerBox.getSelectedItem().toString());
			model.setContents(formatter.getMatchsPlayer(playerID));
			model.fireTableDataChanged();
		}
	}
}
