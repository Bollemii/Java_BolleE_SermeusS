package View;

import Business.ManagerUtils;
import Model.Match;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MatchForm extends JPanel{
	private static final String[] MONTHS = {"janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre"};
	private TournamentFormatter formatter;
	private JPanel formPanel, buttonsPanel;
	private JComboBox<String> tournamentBox, refereeBox, locationBox;
	private JSpinner dateSpinner;
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

		dateSpinner = new JSpinner(new SpinnerDateModel(Date.from(Instant.now()), null, null, Calendar.YEAR));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd-MM-yyyy HH:mm");
		dateSpinner.setEditor(editor);
		formPanel.add(dateSpinner);

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
			dateSpinner.setValue(Date.from(Instant.now()));

			tournamentBox.setSelectedIndex(0);
			refereeBox.setSelectedIndex(0);
			locationBox.setSelectedIndex(0);

			durationSpinner.setValue(0);
			commentText.setText(null);
			finalCheck.setSelected(false);
		} else {
			dateSpinner.setValue(matchSelected.getDateStart().getTime());

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
				GregorianCalendar dateStart = new GregorianCalendar(
						Integer.parseInt(new SimpleDateFormat("yyyy").format(dateSpinner.getValue())),
						Integer.parseInt(new SimpleDateFormat("MM").format(dateSpinner.getValue()))-1,
						Integer.parseInt(new SimpleDateFormat("dd").format(dateSpinner.getValue())),
						Integer.parseInt(new SimpleDateFormat("HH").format(dateSpinner.getValue())),
						Integer.parseInt(new SimpleDateFormat("mm").format(dateSpinner.getValue()))
				);

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
