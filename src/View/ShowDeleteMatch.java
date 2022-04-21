package View;

import Business.TournamentManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ShowDeleteMatch extends JPanel {
    private TournamentManagement manager;
    private JLabel title;
    private JList matchsList;
    private JButton submit;

    //constructor
    public ShowDeleteMatch() {
        manager = new TournamentManagement();

        //gridBagLayout
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(layout);

        //title
        title = new JLabel("Supprimer des matchs");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.NORTH;
        c.weighty = 1;
        add(title, c);

        c.weighty = 2;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 0, 20, 0);
        matchsList = new JList(manager.getMatchsList().toArray(new String[0]));
        matchsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        matchsList.setFont(new Font("Arial", Font.PLAIN, 20));
        add(matchsList, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 20, 0);
        submit = new JButton("Submit");
        submit.addActionListener(new ButtonListener());
        submit.setFont(new Font("Arial", Font.PLAIN, 20));
        add(submit, c);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            manager.deleteMatch(matchsList.getSelectedValuesList());
            ShowDeleteMatch.this.repaint();
        }
    }
}
