package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private Container container;
    private JMenuBar menuBar;
    private JMenu appMenu;
    private JMenuItem exit;
    private JMenu tournament;
    private JMenuItem playerInscription, matchManagement;
    private JMenu match;
    private JMenuItem addMatch,showMatchTable,showAllMatchInfo,modifyMatch,deleteMatch;
    private JMenu player;
    private JMenuItem showPlayer;
    private JMenu visitor;
    private JMenuItem reservation;

    public MainWindow() {
        //set size of window
        setSize(1080, 700);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        appMenu = new JMenu("Application");
        appMenu.setMnemonic('F');
        menuBar.add(appMenu);
        exit = new JMenuItem("Quitter");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        exit.addActionListener(e -> System.exit(0));
        appMenu.add(exit);
        tournament = new JMenu("Tournoi");
        tournament.setMnemonic('T');
        menuBar.add(tournament);
        playerInscription = new JMenuItem("Inscription joueur");
        playerInscription.addActionListener(new HandleShowPanel());
        tournament.add(playerInscription);
        matchManagement = new JMenuItem("Gestion des matchs");
        matchManagement.addActionListener(new HandleShowPanel());
        tournament.add(matchManagement);
        match = new JMenu("Match");
        match.setMnemonic('M');
        menuBar.add(match);
        addMatch = new JMenuItem("Ajouter match");
        addMatch.addActionListener(new HandleShowPanel());
        match.add(addMatch);
        showMatchTable = new JMenuItem("Afficher tableau des matchs");
        showMatchTable.addActionListener(new HandleShowPanel());
        match.add(showMatchTable);
        showAllMatchInfo = new JMenuItem("Afficher toutes les informations des matchs");
        showAllMatchInfo.addActionListener(new HandleShowPanel());
        match.add(showAllMatchInfo);
        modifyMatch = new JMenuItem("Modifier match");
        modifyMatch.addActionListener(new HandleShowPanel());
        match.add(modifyMatch);
        deleteMatch = new JMenuItem("Supprimer match");
        deleteMatch.addActionListener(new HandleShowPanel());
        match.add(deleteMatch);
        player = new JMenu("Joueur");
        player.setMnemonic('J');
        menuBar.add(player);
        showPlayer = new JMenuItem("Afficher joueur");
        showPlayer.addActionListener(new HandleShowPanel());
        player.add(showPlayer);
        visitor = new JMenu("Visiteur");
        visitor.setMnemonic('V');
        menuBar.add(visitor);
        reservation = new JMenuItem("Reservation");
        reservation.addActionListener(new HandleShowPanel());
        visitor.add(reservation);

        container = getContentPane();
        JLabel welcome = new JLabel("Bienvenue dans l'application de gestion de tournoi");
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(welcome);

        //when window closed, exit
        this.addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent e) {System.exit(0);}});

        this.setVisible(true);
    }

    private class HandleShowPanel implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            getContentPane().removeAll();
            JPanel panel = getPanel(e);
            MainWindow.this.add(panel);
            panel.setVisible(true);
            MainWindow.this.validate();
        }

        public JPanel getPanel(ActionEvent e) {
            if (e.getSource() == playerInscription) {
                return new ShowInscription();
            } else if (e.getSource() == matchManagement) {
                return new ShowGestionMatch();
            } else if (e.getSource() == addMatch) {
                return new ShowAddMatch();
            } else if (e.getSource() == showMatchTable) {
                return new ShowMatchTable();
            } else if (e.getSource() == showAllMatchInfo) {
                return new ShowAllMatchInfo();
            } else if (e.getSource() == modifyMatch) {
                return new ShowModifyMatch();
            } else if (e.getSource() == deleteMatch) {
                return new ShowDeleteMatch();
            } else if (e.getSource() == showPlayer) {
                return new ShowPlayer();
            } else if (e.getSource() == reservation) {
                return new ShowReservation();
            }
            return null;
        }
    }
}
