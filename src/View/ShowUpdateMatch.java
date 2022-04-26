package View;

import Business.ManagerUtils;
import Business.TournamentManagement;
import Model.Match;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class ShowUpdateMatch extends JPanel{
	private TournamentManagement manager;
	private JPanel matchChoicePanel;
	private MatchForm formPanel;
	private JLabel title;
	private JComboBox matchsBox;
	private JButton submit;
	private Match matchSelected;

	public ShowUpdateMatch() {
		manager = new TournamentManagement();

		this.setLayout(new BorderLayout());

		title = new JLabel("Modification d'un match", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title, BorderLayout.NORTH);

		matchChoicePanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		matchChoicePanel.setLayout(layout);

		c.insets = new Insets(0, 0, 20, 0);
		matchsBox = new JComboBox(manager.getMatchsList().toArray(new String[0]));
		matchsBox.setFont(new Font("Arial", Font.PLAIN, 20));
		matchChoicePanel.add(matchsBox, c);

		c.insets = new Insets(0, 20, 20, 0);
		submit = new JButton("Valider");
		submit.addActionListener(new ButtonListener());
		submit.setFont(new Font("Arial", Font.PLAIN, 20));
		matchChoicePanel.add(submit, c);

		this.add(matchChoicePanel, BorderLayout.CENTER);
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			matchSelected = manager.getMatch(ManagerUtils.getMatchIDFromDescription(matchsBox.getSelectedItem().toString()));

			formPanel = new MatchForm(false, matchSelected);

			ShowUpdateMatch.this.remove(matchChoicePanel);
			ShowUpdateMatch.this.add(formPanel, BorderLayout.CENTER);
			ShowUpdateMatch.this.validate();
		}
	}
}
