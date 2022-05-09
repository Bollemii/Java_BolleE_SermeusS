package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MatchAnimationWindow extends JFrame {
	private UserInteraction userInteraction;
	private ShowGestionMatch gestionMatch;
	private Container container;
	private ArrayList<MatchAnimationPanel> matchs;

	public MatchAnimationWindow(ShowGestionMatch gestionMatch) {
		super("Match Animation");

		this.userInteraction = new UserInteraction();
		this.gestionMatch = gestionMatch;

		this.setSize(1080, 700);
		//this.setLayout(new GridLayout(4, 4));
		this.setLayout(new GridBagLayout());
		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
				if (userInteraction.displayConfirmation("Voulez-vous abandonner " + (matchs.size() > 1 ? "tous ces matchs" : "ce match") + " ?") == 0) {
					MatchAnimationWindow.this.dispose();
					gestionMatch.removeMatchAnimationWindow();
				}
				}
			}
		);

		matchs = new ArrayList<>();

		container = this.getContentPane();
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for(MatchAnimationPanel match : matchs) {
			container.add(match);
		}
		this.validate();
	}

	/**
	 * Add a match with his animation
	 * @param match description
	 * @param player1 description
	 * @param player2 description
	 */
	public void addMatch(String match, String player1, String player2) {
		matchs.add(new MatchAnimationPanel(this, match, player1, player2));
		this.repaint();
	}

	/**
	 * @param match
	 * @return if the match is currently running
	 */
	public boolean hasMatch(String match) {
		return matchs.stream().map(m -> m.getMatchDescription()).collect(Collectors.toList()).contains(match);
	}

	/**
	 * Remove the match in the animation Window
	 * @param match
	 */
	public void removeMatch(MatchAnimationPanel match) {
		matchs.remove(match);
		container.remove(match);
		if (matchs.size() == 0) {
			this.dispose();
			gestionMatch.removeMatchAnimationWindow();
		} else {
			this.repaint();
		}
	}
}
