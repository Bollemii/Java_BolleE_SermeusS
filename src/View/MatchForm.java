package View;

import Business.ManagerUtils;
import Model.Match;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MatchForm extends JPanel{
	private static final String[] MONTHS = {"janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre"};
	private TournamentFormatter formatter;
	private JPanel formPanel, datePanel, buttonsPanel;
	private JComboBox<String> monthBox, tournamentBox, refereeBox, locationBox;
	private JComboBox<Integer> dayBox, yearBox;
	private JSpinner durationSpinner;
	private JTextArea commentText;
	private JCheckBox finalCheck;
	private JButton validate, reset;
	private boolean isNewMatch;
	private Match matchSelected;

	public MatchForm(boolean isNewMatch, Match matchSelected) {
		formatter = new TournamentFormatter();
		this.isNewMatch = isNewMatch;
		this.matchSelected = matchSelected;

		this.setLayout(new BorderLayout());

		GridBagConstraints c = new GridBagConstraints();
		ButtonListener buttonListener = new ButtonListener();

		formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(7, 2));

		formPanel.add(new JLabel("Date : ", SwingConstants.RIGHT));
		datePanel = new JPanel();
		datePanel.setLayout(new GridBagLayout());
		dayBox = new JComboBox<>();
		for (int i = 1; i <= 31; i++)
			dayBox.addItem(i);
		dayBox.setSelectedItem(LocalDateTime.now().getDayOfMonth());
		datePanel.add(dayBox, c);
		monthBox = new JComboBox<>(MONTHS);
		monthBox.setSelectedItem(MONTHS[LocalDateTime.now().getMonthValue()-1]);
		datePanel.add(monthBox, c);
		yearBox = new JComboBox<>();
		for (int i = 1900; i <= LocalDateTime.now().getYear(); i++)
			yearBox.addItem(i);
		yearBox.setSelectedItem(LocalDateTime.now().getYear());
		datePanel.add(yearBox, c);
		formPanel.add(datePanel);

		formPanel.add(new JLabel("Tournoi : ", SwingConstants.RIGHT));
		tournamentBox = new JComboBox<>(formatter.getTournamentsList().toArray(new String[0]));
		formPanel.add(tournamentBox);

		formPanel.add(new JLabel("Arbitre : ", SwingConstants.RIGHT));
		refereeBox = new JComboBox<>(formatter.getRefereesList().toArray(new String[0]));
		formPanel.add(refereeBox);

		formPanel.add(new JLabel("Localisation : ", SwingConstants.RIGHT));
		locationBox = new JComboBox<>(formatter.getLocationsList().toArray(new String[0]));
		formPanel.add(locationBox);

		formPanel.add(new JLabel("Durée : ", SwingConstants.RIGHT));
		durationSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100000, 1));
		formPanel.add(durationSpinner);

		formPanel.add(new JLabel("Commentaire : ", SwingConstants.RIGHT));
		commentText = new JTextArea();
		formPanel.add(commentText);

		formPanel.add(new JPanel());
		finalCheck = new JCheckBox("est une finale");
		formPanel.add(finalCheck);

		this.add(formPanel, BorderLayout.CENTER);

		buttonsPanel = new JPanel();
		validate = new JButton("Validation");
		validate.setToolTipText("Enregistrer");
		validate.addActionListener(buttonListener);
		buttonsPanel.add(validate);

		reset = new JButton("Réinitialisation");
		reset.setToolTipText("Vider le formulaire");
		reset.addActionListener(buttonListener);
		buttonsPanel.add(reset);

		this.add(buttonsPanel, BorderLayout.SOUTH);
		setValues();
	}

	private void setValues() {
		if (isNewMatch) {
			dayBox.setSelectedItem(LocalDateTime.now().getDayOfMonth());
			monthBox.setSelectedItem(MONTHS[LocalDateTime.now().getMonthValue() - 1]);
			yearBox.setSelectedItem(LocalDateTime.now().getYear());

			tournamentBox.setSelectedIndex(0);
			refereeBox.setSelectedIndex(0);
			locationBox.setSelectedIndex(0);

			durationSpinner.setValue(0);
			commentText.setText(null);
			finalCheck.setSelected(false);
		} else {
			dayBox.setSelectedItem(matchSelected.getDateStart().get(Calendar.DAY_OF_MONTH));
			monthBox.setSelectedIndex(matchSelected.getDateStart().get(Calendar.MONTH));
			yearBox.setSelectedItem(matchSelected.getDateStart().get(Calendar.YEAR));

			tournamentBox.setSelectedItem(matchSelected.getTournament().toString());
			refereeBox.setSelectedItem(matchSelected.getReferee().toString());
			locationBox.setSelectedItem(matchSelected.getLocation().toString());

			durationSpinner.setValue(matchSelected.getDuration() != null ? matchSelected.getDuration() : 0);
			commentText.setText(matchSelected.getComment());
			finalCheck.setSelected(matchSelected.isFinal());
		}
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == validate) {
				GregorianCalendar dateStart = new GregorianCalendar((int)yearBox.getSelectedItem(), monthBox.getSelectedIndex(), (int)dayBox.getSelectedItem());

				Integer tournamentID = ManagerUtils.getIDFromDescription(tournamentBox.getSelectedItem().toString());
				Integer locationID = ManagerUtils.getIDFromDescription(locationBox.getSelectedItem().toString());
				Integer refereeID = ManagerUtils.getIDFromDescription(refereeBox.getSelectedItem().toString());

				int value = (int)durationSpinner.getValue();
				Integer duration = value == 0 ? null : value;
				String text = commentText.getText();
				String comment = text.isBlank() ? null : text;

				if (isNewMatch)
					formatter.addMatch(dateStart, duration, finalCheck.isSelected(), comment, tournamentID, refereeID, locationID);
				else
					formatter.updateMatch(matchSelected.getId(), dateStart, duration, finalCheck.isSelected(), comment, tournamentID, refereeID, locationID);
			} else if (e.getSource() == reset) {
				setValues();
			}
		}
	}
}
