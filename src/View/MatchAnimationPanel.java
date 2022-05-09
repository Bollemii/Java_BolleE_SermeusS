package View;

import View.Pong.Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatchAnimationPanel extends JPanel {
	private MatchAnimationWindow parentWindow;
	private String[] playersDescription;
	private String matchDescription;
	private JLabel matchLabel, playersLabel;
	private Pong pong;
	private JButton finish;

	public MatchAnimationPanel(MatchAnimationWindow parentWindow, String match, String player1, String player2) {
		this.parentWindow = parentWindow;
		this.playersDescription = new String[] {player1, player2};
		this.matchDescription = match;

		this.setLayout(new GridLayout(4, 1));

		matchLabel = new JLabel("Match " + this.matchDescription);
		this.add(matchLabel);
		playersLabel = new JLabel(this.playersDescription[0] + " VS " + this.playersDescription[1]);
		this.add(playersLabel);

		pong = new Pong();
		this.add(pong);

		finish = new JButton("Terminer");
		finish.addActionListener(new ButtonListener());
		this.add(finish);
	}

	public String getMatchDescription() {
		return matchDescription;
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			parentWindow.removeMatch(MatchAnimationPanel.this);
		}
	}
}
