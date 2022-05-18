package View;

import Business.ManagerUtils;
import Exceptions.ValueException;
import Model.Match;
import Model.Player;
import Model.Result;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PointsWindow extends JFrame {
	private final static int WIDTH = 400;
	private final static int HEIGHT = 200;
	private TournamentFormatter formatter;
	private UserInteraction userInteraction;
	private MatchAnimationWindow parentWindow;
	private MatchAnimationPanel parentPanel;
	private String matchDescription, player1Description, player2Description;
	private JFormattedTextField player1Points, player2Points;
	private JButton validate, cancel;

	public PointsWindow(MatchAnimationWindow parentWindow, MatchAnimationPanel parentPanel, String matchDescription, String player1Description, String player2Description) {
		super("Points des joueurs");

		this.formatter = new TournamentFormatter();
		this.userInteraction = new UserInteraction();
		this.parentWindow = parentWindow;
		this.parentPanel = parentPanel;
		this.matchDescription = matchDescription;
		this.player1Description = player1Description;
		this.player2Description = player2Description;
		ButtonListener buttonListener = new ButtonListener();

		this.setSize(WIDTH, HEIGHT);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("pictures/Trophy.png"));

		JLabel title = new JLabel("Points des joueurs", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 20));
		this.add(title, BorderLayout.NORTH);

		JPanel pointsPanel = new JPanel();
		pointsPanel.setLayout(new GridLayout(2, 2));
		JLabel player1Text = new JLabel(player1Description + " : ", SwingConstants.RIGHT);
		pointsPanel.add(player1Text);
		player1Points = new JFormattedTextField();
		player1Points.setValue(0);
		pointsPanel.add(player1Points);
		JLabel player2Text = new JLabel(player2Description + " : ", SwingConstants.RIGHT);
		pointsPanel.add(player2Text);
		player2Points = new JFormattedTextField();
		player2Points.setValue(0);
		pointsPanel.add(player2Points);
		this.add(pointsPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		cancel = new JButton("Annuler");
		cancel.addActionListener(buttonListener);
		buttonPanel.add(cancel);
		validate = new JButton("Valider");
		validate.addActionListener(buttonListener);
		buttonPanel.add(validate);
		this.add(buttonPanel, BorderLayout.SOUTH);

		this.addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent e) {
			PointsWindow.this.dispose();}}
		);

		this.setVisible(true);
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cancel) {
				PointsWindow.this.dispose();
			} else if (e.getSource() == validate) {
				try {
					Match match = new Match(ManagerUtils.getMatchIDFromDescription(matchDescription));
					Player player1 = formatter.getPlayer(ManagerUtils.getIDFromDescription(player1Description));
					Player player2 = formatter.getPlayer(ManagerUtils.getIDFromDescription(player2Description));
					boolean isOK = formatter.addResult(
						new Result(player1, match, (int) player1Points.getValue()),
						new Result(player2, match, (int) player2Points.getValue())
					);
					if (isOK) {
						PointsWindow.this.dispose();
						parentWindow.removeMatch(parentPanel);
					}
				} catch (ValueException exception) {
					userInteraction.displayErrorMessage(exception.getMessage());
				}
			}
		}
	}
}
