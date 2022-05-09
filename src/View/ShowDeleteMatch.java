package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowDeleteMatch extends JPanel {
	private TournamentFormatter formatter;
	private UserInteraction userInteraction;
	private JPanel mainPanel;
	private JLabel title;
	private JList matchsList;
	private JButton submit;

	public ShowDeleteMatch() {
		formatter = new TournamentFormatter();
		userInteraction = new UserInteraction();

		this.setLayout(new BorderLayout());

		title = new JLabel("Suppression de matchs", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title, BorderLayout.NORTH);

		mainPanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		mainPanel.setLayout(layout);

		constraints.insets = new Insets(0, 0, 20, 0);
		matchsList = new JList(formatter.getMatchsList().toArray(new String[0]));
		matchsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		matchsList.setFont(new Font("Arial", Font.PLAIN, 20));
		mainPanel.add(matchsList, constraints);

		constraints.insets = new Insets(0, 20, 20, 0);
		submit = new JButton("Valider");
		submit.addActionListener(new ButtonListener());
		submit.setFont(new Font("Arial", Font.PLAIN, 20));
		mainPanel.add(submit, constraints);

		this.add(mainPanel, BorderLayout.CENTER);
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (userInteraction.displayConfirmation("Êtes-vous sûr de vouloir supprimer ?") == 0) {
				formatter.deleteMatch(matchsList.getSelectedValuesList());
				matchsList.setListData(formatter.getMatchsList().toArray(new String[0]));
			}
		}
	}
}
