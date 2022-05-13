package View;

import Business.ManagerUtils;
import View.TableModels.MatchsTournamentModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowMatchsTournament extends JPanel {
    private TournamentFormatter formatter;
    private JPanel tournamentPanel;
    private JLabel title;
    private JTable table;
    private MatchsTournamentModel model;
    private JComboBox<String> tournamentBox;
    private JButton submit;

    public ShowMatchsTournament() {
        formatter = new TournamentFormatter();
        this.setLayout(new BorderLayout());

        title = new JLabel("Matchs et joueurs d'un tournoi", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        this.add(title, BorderLayout.NORTH);

        model = new MatchsTournamentModel();
        table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 15));
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        tournamentPanel = new JPanel();

        tournamentBox = new JComboBox<>(formatter.getTournamentsList().toArray(new String[0]));
        tournamentPanel.add(tournamentBox);
        submit = new JButton("Valider");
        submit.addActionListener(new ButtonListener());
        tournamentPanel.add(submit);
        this.add(tournamentPanel, BorderLayout.SOUTH);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int tournamentID = ManagerUtils.getIDFromDescription(tournamentBox.getSelectedItem().toString());
            model.setContents(formatter.getMatchsTournament(tournamentID));
            model.fireTableDataChanged();
        }
    }
}
