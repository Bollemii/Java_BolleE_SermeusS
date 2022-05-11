package View;

import View.Pong.Pong;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatchAnimationPanel extends JPanel {
	private MatchAnimationWindow parentWindow;
	private Border border, margin;
	private String[] playersDescription;
	private String matchDescription;
	private JLabel matchLabel, playersLabel;
	private Pong pong;
	private JButton finish;

	public MatchAnimationPanel(MatchAnimationWindow parentWindow, String match, String player1, String player2) {
		this.parentWindow = parentWindow;
		this.playersDescription = new String[] {player1, player2};
		this.matchDescription = match;

		this.setSize(225, 500);
		this.setLayout(new BorderLayout());

		border = BorderFactory.createRaisedBevelBorder();
		margin = new EmptyBorder(10,10,10,10);
		this.setBorder(new CompoundBorder(border, margin));

		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BorderLayout());
		matchLabel = new JLabel("Match " + this.matchDescription, SwingConstants.CENTER);
		labelPanel.add(matchLabel, BorderLayout.NORTH);
		playersLabel = new JLabel(this.playersDescription[0] + " VS " + this.playersDescription[1], SwingConstants.CENTER);
		labelPanel.add(playersLabel, BorderLayout.SOUTH);
		this.add(labelPanel, BorderLayout.NORTH);

		pong = new Pong();
		this.add(pong, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		finish = new JButton("Terminer");
		finish.addActionListener(new ButtonListener());
		buttonPanel.add(finish);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * @return description of the match
	 */
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
