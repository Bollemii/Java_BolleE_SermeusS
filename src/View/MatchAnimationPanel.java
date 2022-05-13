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
	private final static int WIDTH = 250;
	private final static int HEIGHT = 380;
	private MatchAnimationWindow parentWindow;
	private Border border, margin;
	private String[] playersDescription;
	private String matchDescription;
	private Pong pong;
	private JButton finish;

	public MatchAnimationPanel(MatchAnimationWindow parentWindow, int x, int y, String match, String player1, String player2) {
		this.parentWindow = parentWindow;
		this.playersDescription = new String[] {player1, player2};
		this.matchDescription = match;

		this.setBounds(x, y, WIDTH, HEIGHT);
		this.setLayout(new BorderLayout());

		border = BorderFactory.createRaisedBevelBorder();
		margin = new EmptyBorder(10,10,10,10);
		this.setBorder(new CompoundBorder(border, margin));

		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(4, 1));
		labelPanel.add(new JLabel("Match " + this.matchDescription, SwingConstants.CENTER));
		labelPanel.add(new JLabel(this.playersDescription[0], SwingConstants.CENTER));
		labelPanel.add(new JLabel("VS", SwingConstants.CENTER));
		labelPanel.add(new JLabel(this.playersDescription[1], SwingConstants.CENTER));
		this.add(labelPanel, BorderLayout.NORTH);

		pong = new Pong(WIDTH-25);
		this.add(pong, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		finish = new JButton("Terminer");
		finish.addActionListener(new ButtonListener());
		buttonPanel.add(finish);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	public static int getWIDTH() {
		return WIDTH;
	}
	public static int getHEIGHT() {
		return HEIGHT;
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
