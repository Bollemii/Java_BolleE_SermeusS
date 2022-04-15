package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;

public class Form extends JPanel {
	private static final String[] ORIGINS = {"Europe", "Afrique", "Asie", "Amérique", "Océanie"};
	private static final String[] MONTHS = {"janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre"};
	private JPanel formPanel, datePanel, buttonsPanel;
	private JTextField firstName, lastName, birthDate, section;
	private JCheckBox boursier, etranger;
	private JComboBox<String> origins, monthBox;
	private JComboBox<Integer> dayBox, yearBox;
	private JRadioButton newStudent, reStudent;
	private JButton back, validate, reset;

	public Form() {
		setLayout(new BorderLayout());

		CheckListener checkListener = new CheckListener();
		RadioListener radioListener = new RadioListener();
		ButtonListener buttonListener = new ButtonListener();
		GridBagConstraints c = new GridBagConstraints();

		formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(8, 2));

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

		formPanel.add(new JLabel("Prénom : ", SwingConstants.RIGHT));
		firstName = new JTextField();
		formPanel.add(firstName);

		formPanel.add(new JLabel("Nom : ", SwingConstants.RIGHT));
		lastName = new JTextField();
		formPanel.add(lastName);

		formPanel.add(new JLabel("Date de naissance : ", SwingConstants.RIGHT));
		birthDate = new JTextField();
		birthDate.setToolTipText("Format : YYYYMMJJ");
		formPanel.add(birthDate);

		formPanel.add(new JLabel("Section : ", SwingConstants.RIGHT));
		section = new JTextField();
		section.setEnabled(false);
		formPanel.add(section);

		boursier = new JCheckBox("Boursier");
		formPanel.add(boursier);
		etranger = new JCheckBox("Etranger");
		etranger.setToolTipText("Personne hors Europe");
		etranger.addItemListener(checkListener);
		formPanel.add(etranger);

		formPanel.add(new JLabel("Origine : ", SwingConstants.RIGHT));
		origins = new JComboBox<>(ORIGINS);
		origins.setSelectedItem("Europe");
		origins.setEnabled(false);
		formPanel.add(origins);

		ButtonGroup group = new ButtonGroup();
		newStudent = new JRadioButton("Nouvel étudiant", true);
		newStudent.addItemListener(radioListener);
		group.add(newStudent);
		formPanel.add(newStudent);
		reStudent = new JRadioButton("Réinscription", false);
		reStudent.setToolTipText("Etudiant déjà inscrit l'année dernière");
		reStudent.addItemListener(radioListener);
		group.add(reStudent);
		formPanel.add(reStudent);
		this.add(formPanel, BorderLayout.CENTER);

		buttonsPanel = new JPanel();
		back = new JButton("Retour");
		back.setToolTipText("Retour à la fenêtre d'accueil");
		buttonsPanel.add(back);

		validate = new JButton("Validation");
		validate.setToolTipText("Enregistrer l'étudiant");
		buttonsPanel.add(validate);

		reset = new JButton("Réinitialisation");
		reset.setToolTipText("Vider le formulaire");
		reset.addActionListener(buttonListener);
		buttonsPanel.add(reset);

		this.add(buttonsPanel, BorderLayout.SOUTH);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	private class CheckListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				origins.setEnabled(true);
			} else {
				origins.setSelectedItem("Europe");
				origins.setEnabled(false);
			}
		}
	}
	private class RadioListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == newStudent && e.getStateChange() == ItemEvent.SELECTED && boursier.isSelected()) {
				JOptionPane.showMessageDialog(
						null,
						"Vous devez vous rendre au secrétariat",
						"Rendez-vous",
						JOptionPane.INFORMATION_MESSAGE
				);
			} else if (e.getSource() == reStudent && e.getStateChange() == ItemEvent.SELECTED) {
				JOptionPane.showMessageDialog(
						null,
						"Ne tardez pas à remplir votre PAE",
						"Rappel",
						JOptionPane.INFORMATION_MESSAGE
				);
			}
		}
	}
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == reset) {
				firstName.setText(null);
				lastName.setText(null);
				birthDate.setText(null);
				section.setText(null);

				boursier.setSelected(false);
				etranger.setSelected(false);

				newStudent.setSelected(true);
			}
		}
	}
}
