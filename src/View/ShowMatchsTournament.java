package View;

import Business.ManagerUtils;
import Business.TournamentManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowMatchsTournament extends JPanel {
    private TournamentManagement manager;
    private JPanel tournamentPanel;
    private JLabel title;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> tournamentBox;
    private JButton submit;

    public ShowMatchsTournament() {
        manager = new TournamentManagement();
        this.setLayout(new BorderLayout());

        // title
        title = new JLabel("Matchs et joueurs d'un tournoi", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        this.add(title, BorderLayout.NORTH);

        // table
        String[] tableHead = {"Date de début", "Prénom", "Nom", "Elo", "Points"};
        tableModel = new DefaultTableModel(tableHead, 0);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 15));
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        tournamentPanel = new JPanel();

        tournamentBox = new JComboBox<>(manager.getTournamentsList().toArray(new String[0]));
        tournamentPanel.add(tournamentBox);
        submit = new JButton("Submit");
        submit.addActionListener(new ButtonListener());
        tournamentPanel.add(submit);
        this.add(tournamentPanel, BorderLayout.SOUTH);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            tableModel.setRowCount(0);
            int tournamentID = ManagerUtils.getIDFromDescription(tournamentBox.getSelectedItem().toString());
            for (String[] match : manager.getMatchsTournament(tournamentID)) {
                tableModel.addRow(match);
            }
        }
    }
}
