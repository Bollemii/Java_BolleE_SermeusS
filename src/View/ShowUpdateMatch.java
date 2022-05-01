package View;

import Business.ManagerUtils;
import Model.Match;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowUpdateMatch extends JPanel{
	private TournamentFormatter formatter;
	private JPanel matchChoicePanel, mainPanel;
	private MatchForm formPanel;
	private JLabel title;
	private JComboBox matchsBox;
	private JButton submit;
	private Match matchSelected;

	public ShowUpdateMatch() {
		formatter = new TournamentFormatter();

		this.setLayout(new BorderLayout());

		title = new JLabel("Modification d'un match", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 40));
		this.add(title, BorderLayout.NORTH);

		matchChoicePanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		matchChoicePanel.setLayout(layout);

		c.insets = new Insets(0, 0, 20, 0);
		matchsBox = new JComboBox(formatter.getMatchsList().toArray(new String[0]));
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
			matchSelected = formatter.getMatch(ManagerUtils.getMatchIDFromDescription(matchsBox.getSelectedItem().toString()));

			mainPanel = new JPanel();
			mainPanel.setLayout(new GridBagLayout());

			formPanel = new MatchForm(false, matchSelected);
			mainPanel.add(formPanel);

			ShowUpdateMatch.this.remove(matchChoicePanel);
			ShowUpdateMatch.this.add(mainPanel, BorderLayout.CENTER);
			ShowUpdateMatch.this.validate();
		}
	}
}
