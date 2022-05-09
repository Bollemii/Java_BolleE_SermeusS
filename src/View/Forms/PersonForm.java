package View.Forms;

import Business.ManagerUtils;
import View.TournamentFormatter;
import View.UserInteraction;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;

																		// A FAIRE

public class PersonForm extends JPanel {
	private TournamentFormatter formatter;
	private UserInteraction userInteraction;
	private Border border, margin;
	private JPanel formPanel, buttonsPanel;
	private JComboBox<String> typePersonBox, visitorBox, matchBox;
	private JRadioButton playerRadio, visitorRadio, refereeRadio, manRadio, womanRadio, otherRadio;
	private JTextField firstNameText, lastNameText, levelText, seatTypeText;
	private JSpinner birthDateSpinner, eloSpinner, seatNumberSpinner;
	private JFormattedTextField seatRowText, costText;
	private JCheckBox professionalCheck, vipCheck;
	private JButton validate, reset;

	public PersonForm() {
		this.formatter = new TournamentFormatter();
		this.userInteraction = new UserInteraction();

		this.setLayout(new BorderLayout());
		this.setBackground(new Color(255,250,205));
		border = BorderFactory.createRaisedBevelBorder();
		margin = new EmptyBorder(10,10,10,10);
		this.setBorder(new CompoundBorder(border, margin));

		ButtonListener buttonListener = new ButtonListener();

		formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(7, 2, 10, 10));
		formPanel.setOpaque(false);

		formPanel.add(new JLabel("Type de personne : ", SwingConstants.RIGHT));
		typePersonBox = new JComboBox<>(new String[]{"Player", "Visiteur", "Arbitre"});
		formPanel.add(typePersonBox);

		formPanel.add(new JLabel("Prénom : ", SwingConstants.RIGHT));
		matchBox = new JComboBox<>(formatter.getMatchsList().toArray(new String[0]));
		formPanel.add(matchBox);

		formPanel.add(new JLabel("Nom : ", SwingConstants.RIGHT));
		seatTypeText = new JTextField();
		formPanel.add(seatTypeText);

		formPanel.add(new JLabel("Date de naissance : ", SwingConstants.RIGHT));
		try {
			seatRowText = new JFormattedTextField(new MaskFormatter("?"));
			formPanel.add(seatRowText);
		} catch (ParseException exception) {
			userInteraction.displayErrorMessage(exception.getMessage());
		}

		formPanel.add(new JLabel("Genre : ", SwingConstants.RIGHT));
		seatNumberSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
		formPanel.add(seatNumberSpinner);

		formPanel.add(new JLabel("est professionnel : ", SwingConstants.RIGHT));
		costText = new JFormattedTextField(new DecimalFormat("#0.0#"));
		formPanel.add(costText);

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
	}

	/**
	 * test if all of required fields have a value
	 * @return if all of required fields have a value
	 */
	private boolean validateRequiredFields() {
		String errorMessage = "";
		if (seatTypeText.getText().isBlank()) {
			errorMessage += "\n  - Le type de siège n'est pas spécifié";
		}
		if (seatRowText.getText().isBlank()) {
			errorMessage += "\n  - La rangée du siège n'est pas spécifiée";
		}
		if (costText.getText().isBlank()) {
			errorMessage += "\n  - Le prix de la réservation n'est pas spécifié";
		}

		if (errorMessage != "") {
			userInteraction.displayErrorMessage("Des champs obligatoires ne sont pas remplis :" + errorMessage);
			return false;
		}
		return true;
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == validate) {
				if (validateRequiredFields()) {
					Integer visitorID = ManagerUtils.getIDFromDescription(visitorBox.getSelectedItem().toString());
					Integer matchID = ManagerUtils.getMatchIDFromDescription(matchBox.getSelectedItem().toString());

					formatter.addReservation(visitorID, matchID, seatTypeText.getText(), seatRowText.getText().toUpperCase().charAt(0), (int)seatNumberSpinner.getValue(), Double.parseDouble(costText.getValue().toString()));
				}
			} else if (e.getSource() == reset) {
				visitorBox.setSelectedIndex(0);
				matchBox.setSelectedIndex(0);

				seatTypeText.setText(null);
				seatRowText.setText(null);
				seatNumberSpinner.setValue(1);
				costText.setText(null);
			}
		}
	}
}
