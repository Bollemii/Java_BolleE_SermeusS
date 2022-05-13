package View;

import Model.Match;
import View.TableModels.AllMatchsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowDeleteMatch extends JPanel {
	private TournamentFormatter formatter;
	private UserInteraction userInteraction;
	private JLabel title;
	private JTable matchsTable;
	private AllMatchsModel model;
	private JButton submit;

	public ShowDeleteMatch() {
		formatter = new TournamentFormatter();
		userInteraction = new UserInteraction();

		this.setLayout(new BorderLayout());

		title = new JLabel("Suppression de matchs", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title, BorderLayout.NORTH);

		model = new AllMatchsModel(formatter.getAllMatchs());
		matchsTable = new JTable(model);
		matchsTable.setRowHeight(30);
		matchsTable.setFont(new Font("Arial", Font.PLAIN, 15));
		this.add(new JScrollPane(matchsTable), BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		submit = new JButton("Valider");
		submit.addActionListener(new ButtonListener());
		submit.setFont(new Font("Arial", Font.PLAIN, 20));
		buttonPanel.add(submit);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (userInteraction.displayConfirmation("Êtes-vous sûr de vouloir supprimer ?") == 0) {
				ArrayList<Match> matchs = new ArrayList<>();
				for (int iRow : matchsTable.getSelectedRows()) {
					matchs.add(model.getValuesRow(iRow));
				}
				formatter.deleteMatch(matchs);
				model.setContents(formatter.getAllMatchs());
				model.fireTableDataChanged();
			}
		}
	}
}
