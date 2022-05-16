package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MatchAnimationWindow extends JFrame {
	private final static int WIDTH = 1065;
	private final static int HEIGHT = 820;
	private final static int NB_MATCHS_WIDTH = 4;
	private final static int NB_MATCHS_HEIGHT = 2;
	private final static int MATCH_PADDING = 10;
	private UserInteraction userInteraction;
	private ShowGestionMatch gestionMatch;
	private Container container;
	private JPanel mainPanel;
	private MatchAnimationPanel[][] matchs;

	public MatchAnimationWindow(ShowGestionMatch gestionMatch) {
		super("Match Animation");

		this.container = this.getContentPane();
		this.userInteraction = new UserInteraction();
		this.gestionMatch = gestionMatch;
		this.matchs = new MatchAnimationPanel[NB_MATCHS_HEIGHT][NB_MATCHS_WIDTH];

		this.setSize(WIDTH, HEIGHT);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
				if (userInteraction.displayConfirmation("Voulez-vous abandonner " + (nbMatchs() > 1 ? "tous ces matchs" : "ce match") + " ?") == 0) {
					MatchAnimationWindow.this.dispose();
					MatchAnimationWindow.this.gestionMatch.removeMatchAnimationWindow();
				}
				}
			}
		);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		container.add(new JScrollPane(mainPanel), BorderLayout.CENTER);

		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		mainPanel.removeAll();
		for(int i = 0; i < NB_MATCHS_HEIGHT; i++) {
			for(int j = 0; j < NB_MATCHS_WIDTH; j++) {
				if (matchs[i][j] != null)
					mainPanel.add(matchs[i][j]);
			}
		}
		this.validate();
	}

	/**
	 * Get the first match available
	 * @return iLine *10 + iColumn
	 * if no match is available, return -1
	 */
	private int getAvailableMatch() {
		for(int i = 0; i < NB_MATCHS_HEIGHT; i++) {
			for(int j = 0; j < NB_MATCHS_WIDTH; j++) {
				if (matchs[i][j] == null)
					return i*10 + j;
			}
		}
		return -1;
	}

	/**
	 * get nb matchs are running
	 * @return nb matchs running
	 */
	private int nbMatchs() {
		int nbMatchs = 0;
		for(int i = 0; i < NB_MATCHS_HEIGHT; i++) {
			for(int j = 0; j < NB_MATCHS_WIDTH; j++) {
				if (matchs[i][j] != null)
					nbMatchs++;
			}
		}
		return nbMatchs;
	}

	/**
	 * Add a match with his animation
	 * @param match description
	 * @param player1 description
	 * @param player2 description
	 */
	public void addMatch(String match, String player1, String player2) {
		int iCell = getAvailableMatch();
		if (iCell != -1) {
			matchs[iCell / 10][iCell % 10] = new MatchAnimationPanel(
				this,
				MATCH_PADDING + (iCell % 10) * (MatchAnimationPanel.getWIDTH() + MATCH_PADDING),
				MATCH_PADDING + (iCell / 10) * (MatchAnimationPanel.getHEIGHT() + MATCH_PADDING),
				match,
				player1,
				player2
			);
			this.repaint();
		} else {
			userInteraction.displayErrorMessage("Plus d'emplacement disponible");
		}
	}

	/**
	 * @param match
	 * @return if the match is currently running
	 */
	public boolean hasMatch(String match) {
		for(int i = 0; i < NB_MATCHS_HEIGHT; i++) {
			for(int j = 0; j < NB_MATCHS_WIDTH; j++) {
				if (matchs[i][j] != null && matchs[i][j].getMatchDescription() == match)
					return true;
			}
		}
		return false;
	}

	/**
	 * Remove the match in the animation Window
	 * @param match
	 */
	public void removeMatch(MatchAnimationPanel match) {
		for(int i = 0; i < NB_MATCHS_HEIGHT; i++) {
			for(int j = 0; j < NB_MATCHS_WIDTH; j++) {
				if (matchs[i][j] != null && matchs[i][j] == match)
					matchs[i][j] = null;
			}
		}
		mainPanel.remove(match);
		gestionMatch.updateMatchs();
		if (nbMatchs() == 0) {
			this.dispose();
			gestionMatch.removeMatchAnimationWindow();
		} else {
			this.repaint();
		}
	}
}
