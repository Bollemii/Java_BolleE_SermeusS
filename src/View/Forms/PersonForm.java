package View.Forms;

import View.TournamentFormatter;
import View.UserInteraction;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PersonForm extends JPanel {								// A FAIRE
	private TournamentFormatter formatter;
	private UserInteraction userInteraction;
	private Border border, margin;
	private JPanel formPanel, typePanel, genderPanel, buttonsPanel;
	private JLabel professionalLabel, eloLabel, vipLabel, levelLabel;
	private JRadioButton playerRadio, visitorRadio, refereeRadio, manRadio, womanRadio, otherRadio;
	private JTextField firstNameText, lastNameText, levelText;
	private JSpinner birthDateSpinner, eloSpinner;
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
		RadioListener radioListener = new RadioListener();

		formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(9, 2, 10, 10));
		formPanel.setOpaque(false);

		formPanel.add(new JLabel("Type de personne : ", SwingConstants.RIGHT));
		typePanel = new JPanel();
		ButtonGroup typeGroup = new ButtonGroup();
		playerRadio = new JRadioButton("Joueur", true);
		typeGroup.add(playerRadio);
		playerRadio.setOpaque(false);
		playerRadio.addItemListener(radioListener);
		typePanel.add(playerRadio);
		visitorRadio = new JRadioButton("Visiteur", false);
		typeGroup.add(visitorRadio);
		visitorRadio.setOpaque(false);
		visitorRadio.addItemListener(radioListener);
		typePanel.add(visitorRadio);
		refereeRadio = new JRadioButton("Arbitre", false);
		typeGroup.add(refereeRadio);
		refereeRadio.setOpaque(false);
		typePanel.add(refereeRadio);
		refereeRadio.addItemListener(radioListener);
		typePanel.setOpaque(false);
		formPanel.add(typePanel);

		formPanel.add(new JLabel("Genre : ", SwingConstants.RIGHT));
		genderPanel = new JPanel();
		ButtonGroup genderGroup = new ButtonGroup();
		manRadio = new JRadioButton("Homme", true);
		genderGroup.add(manRadio);
		manRadio.setOpaque(false);
		genderPanel.add(manRadio);
		womanRadio = new JRadioButton("Femme", false);
		genderGroup.add(womanRadio);
		womanRadio.setOpaque(false);
		genderPanel.add(womanRadio);
		otherRadio = new JRadioButton("Autre", false);
		genderGroup.add(otherRadio);
		otherRadio.setOpaque(false);
		genderPanel.add(otherRadio);
		genderPanel.setOpaque(false);
		formPanel.add(genderPanel);


		formPanel.add(new JLabel("Prénom : ", SwingConstants.RIGHT));
		firstNameText = new JTextField();
		formPanel.add(firstNameText);

		formPanel.add(new JLabel("Nom : ", SwingConstants.RIGHT));
		lastNameText = new JTextField();
		formPanel.add(lastNameText);

		formPanel.add(new JLabel("Date de naissance : ", SwingConstants.RIGHT));
		birthDateSpinner = new JSpinner(new SpinnerDateModel(new GregorianCalendar(2000, 01, 01).getTime(), null, Date.from(Instant.now()), Calendar.YEAR));
		birthDateSpinner.setEditor(new JSpinner.DateEditor(birthDateSpinner, "dd-MM-yyyy"));
		formPanel.add(birthDateSpinner);

		// Player fields
		professionalLabel = new JLabel("Est professionnel : ", SwingConstants.RIGHT);
		formPanel.add(professionalLabel);
		professionalCheck = new JCheckBox();
		professionalCheck.setOpaque(false);
		formPanel.add(professionalCheck);

		eloLabel = new JLabel("Elo : ", SwingConstants.RIGHT);
		formPanel.add(eloLabel);
		eloSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100000, 1));
		formPanel.add(eloSpinner);

		// Visitor field
		vipLabel = new JLabel("Est VIP : ", SwingConstants.RIGHT);
		formPanel.add(vipLabel);
		vipCheck = new JCheckBox();
		vipCheck.setOpaque(false);
		formPanel.add(vipCheck);

		// Referee field
		levelLabel = new JLabel("Niveau : ", SwingConstants.RIGHT);
		formPanel.add(levelLabel);
		levelText = new JTextField();
		formPanel.add(levelText);

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

		setPlayerFieldsVisible();
	}

	private void setPlayerFieldsVisible() {
		professionalLabel.setVisible(true);
		professionalCheck.setVisible(true);
		eloLabel.setVisible(true);
		eloSpinner.setVisible(true);
		vipLabel.setVisible(false);
		vipCheck.setVisible(false);
		levelLabel.setVisible(false);
		levelText.setVisible(false);
	}

	/**
	 * test if all of required fields have a value
	 * @return if all of required fields have a value
	 */
	private boolean validateRequiredFields() {
		String errorMessage = "";
		if (firstNameText.getText().isBlank()) {
			errorMessage += "\n  - Le prénom n'est pas spécifié";
		}
		if (lastNameText.getText().isBlank()) {
			errorMessage += "\n  - La nom n'est pas spécifié";
		}
		if (refereeRadio.isSelected() && levelText.getText().isBlank()) {
			errorMessage += "\n  - Le niveau d'arbitre n'est pas spécifié";
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
					GregorianCalendar birthDate = new GregorianCalendar(
							Integer.parseInt(new SimpleDateFormat("yyyy").format(birthDateSpinner.getValue())),
							Integer.parseInt(new SimpleDateFormat("MM").format(birthDateSpinner.getValue())) - 1,
							Integer.parseInt(new SimpleDateFormat("dd").format(birthDateSpinner.getValue()))
					);

					formatter.addPerson(getType(), firstNameText.getText(), lastNameText.getText(), getGender(), birthDate, professionalCheck.isSelected(), (int)eloSpinner.getValue(), vipCheck.isSelected(), levelText.getText());
				}
			} else if (e.getSource() == reset) {
				playerRadio.setSelected(true);
				manRadio.setSelected(true);
				firstNameText.setText(null);
				lastNameText.setText(null);
				birthDateSpinner.setValue(new GregorianCalendar(2000, 01, 01).getTime());
				professionalCheck.setSelected(false);
				eloSpinner.setValue(0);
				vipCheck.setSelected(false);
				levelText.setText(null);

				setPlayerFieldsVisible();
			}
		}

		private char getGender() {
			if (manRadio.isSelected()) {
				return 'M';
			} else if (womanRadio.isSelected()) {
				return 'F';
			} else {
				return 'X';
			}
		}

		private String getType() {
			if (playerRadio.isSelected()) {
				return "Player";
			} else if (visitorRadio.isSelected()) {
				return "Visitor";
			} else {
				return "Referee";
			}
		}
	}
	private class RadioListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (playerRadio.isSelected()) {
				setPlayerFieldsVisible();
			} else if (visitorRadio.isSelected()) {
				professionalLabel.setVisible(false);
				professionalCheck.setVisible(false);
				eloLabel.setVisible(false);
				eloSpinner.setVisible(false);
				vipLabel.setVisible(true);
				vipCheck.setVisible(true);
				levelLabel.setVisible(false);
				levelText.setVisible(false);
			} else if (refereeRadio.isSelected()) {
				professionalLabel.setVisible(false);
				professionalCheck.setVisible(false);
				eloLabel.setVisible(false);
				eloSpinner.setVisible(false);
				vipLabel.setVisible(false);
				vipCheck.setVisible(false);
				levelLabel.setVisible(true);
				levelText.setVisible(true);
			}
		}
	}
}
