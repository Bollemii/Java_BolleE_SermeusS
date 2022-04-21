package View;

import Business.ManagerUtils;
import Business.TournamentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;

public class ShowAddMatch extends JPanel {
    private static final String[] MONTHS = {"janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "décembre"};
    private TournamentManagement manager;
    private JPanel formPanel, datePanel, buttonsPanel;
    private JComboBox<String> monthBox, tournamentBox, refereeBox, locationBox;
    private JComboBox<Integer> dayBox, yearBox;
    private JSpinner durationSpinner;
    private JTextArea commentText;
    private JCheckBox finalCheck;
    private JButton validate, reset;

    public ShowAddMatch() {
        manager = new TournamentManagement();

        setLayout(new BorderLayout());

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
        tournamentBox = new JComboBox<>(manager.getTournamentsList().toArray(new String[0]));
        formPanel.add(tournamentBox);

        formPanel.add(new JLabel("Arbitre : ", SwingConstants.RIGHT));
        refereeBox = new JComboBox<>(manager.getRefereesList().toArray(new String[0]));
        formPanel.add(refereeBox);

        formPanel.add(new JLabel("Localisation : ", SwingConstants.RIGHT));
        locationBox = new JComboBox<>(manager.getLocationsList().toArray(new String[0]));
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
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == validate) {
                GregorianCalendar dateStart = new GregorianCalendar((int)yearBox.getSelectedItem(), monthBox.getSelectedIndex(), (int)dayBox.getSelectedItem());

                Integer tournamentID = ManagerUtils.getPersonIDFromDescription(tournamentBox.getSelectedItem().toString());
                Integer locationID = ManagerUtils.getPersonIDFromDescription(locationBox.getSelectedItem().toString());
                Integer refereeID = ManagerUtils.getPersonIDFromDescription(refereeBox.getSelectedItem().toString());

                int value = (int)durationSpinner.getValue();
                Integer duration = value == 0 ? null : value;
                String text = commentText.getText();
                String comment = text.isBlank() ? null : text;

                manager.addMatch(dateStart, duration, finalCheck.isSelected(), comment, tournamentID, refereeID, locationID);
            } else if (e.getSource() == reset) {
                dayBox.setSelectedItem(LocalDateTime.now().getDayOfMonth());
                monthBox.setSelectedItem(MONTHS[LocalDateTime.now().getMonthValue()-1]);
                yearBox.setSelectedItem(LocalDateTime.now().getYear());

                tournamentBox.setSelectedIndex(0);
                refereeBox.setSelectedIndex(0);
                locationBox.setSelectedIndex(0);

                durationSpinner.setValue(0);
                commentText.setText(null);
                finalCheck.setSelected(false);
            }
        }
    }
}
