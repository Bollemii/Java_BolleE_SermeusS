package View.Forms;

import Business.ManagerUtils;
import Model.Match;
import View.TournamentFormatter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MatchForm extends JPanel{
	private TournamentFormatter formatter;
	private Border border, margin;
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
		this.formatter = new TournamentFormatter();
		this.isNewMatch = isNewMatch;
		this.matchSelected = matchSelected;

		this.setLayout(new BorderLayout());
		this.setBackground(new Color(255,250,205));
		border = BorderFactory.createRaisedBevelBorder();
		margin = new EmptyBorder(10,10,10,10);
		this.setBorder(new CompoundBorder(border, margin));

		ButtonListener buttonListener = new ButtonListener();

		formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(8, 2, 10, 10));
		formPanel.setOpaque(false);

		formPanel.add(new JLabel("Date : ", SwingConstants.RIGHT));
		dateSpinner = new JSpinner(new SpinnerDateModel(Date.from(Instant.now()), null, null, Calendar.YEAR));
		dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd-MM-yyyy HH:mm"));
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

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		formPanel.add(panel);
		finalCheck = new JCheckBox("est une finale");
		finalCheck.setOpaque(false);
		formPanel.add(finalCheck);

		this.add(formPanel, BorderLayout.CENTER);

		buttonsPanel = new JPanel();
		buttonsPanel.setOpaque(false);
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

	/**
	 * set default values from the form
	 */
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
