package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private TournamentFormatter formatter;
    private Container container;
    private JMenuBar menuBar;
    private JMenu appMenu;
    private JMenuItem exit;
    private JMenu tournament;
    private JMenuItem playerInscription, showMatchsTournament, matchManagement;
    private JMenu match;
    private JMenuItem addMatch,showMatchTable,modifyMatch,deleteMatch;
    private JMenu player;
    private JMenuItem showPlayer, showMatchsPlayer;
    private JMenu visitor;
    private JMenuItem reservation, showReservation;

    public MainWindow() {
        super("Gestion de tournois");

        formatter = new TournamentFormatter();

        // set size of window
        this.setSize(1080, 700);
        this.setLocationRelativeTo(null);

        // menu
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        appMenu = new JMenu("Application");
        appMenu.setMnemonic('F');
        menuBar.add(appMenu);

        exit = new JMenuItem("Quitter");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        exit.addActionListener(e -> {
            formatter.closeConnection();System.exit(0);}
        );
        appMenu.add(exit);

        tournament = new JMenu("Tournoi");
        tournament.setMnemonic('T');
        menuBar.add(tournament);

        playerInscription = new JMenuItem("Inscription joueur");
        playerInscription.addActionListener(new HandleShowPanel());
        tournament.add(playerInscription);

        showMatchsTournament = new JMenuItem("Afficher matchs et joueurs d'un tournoi");
        showMatchsTournament.addActionListener(new HandleShowPanel());
        tournament.add(showMatchsTournament);

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

        modifyMatch = new JMenuItem("Modifier match");
        modifyMatch.addActionListener(new HandleShowPanel());
        match.add(modifyMatch);

        deleteMatch = new JMenuItem("Supprimer match");
        deleteMatch.addActionListener(new HandleShowPanel());
        match.add(deleteMatch);

        player = new JMenu("Joueur");
        player.setMnemonic('J');
        menuBar.add(player);

        showPlayer = new JMenuItem("Afficher tableau des joueurs");
        showPlayer.addActionListener(new HandleShowPanel());
        player.add(showPlayer);

        showMatchsPlayer = new JMenuItem("Afficher matchs d'un joueur");
        showMatchsPlayer.addActionListener(new HandleShowPanel());
        player.add(showMatchsPlayer);

        visitor = new JMenu("Visiteur");
        visitor.setMnemonic('V');
        menuBar.add(visitor);

        reservation = new JMenuItem("Réservation");
        reservation.addActionListener(new HandleShowPanel());
        visitor.add(reservation);

        showReservation = new JMenuItem("Afficher réservations d'un visiteur");
        showReservation.addActionListener(new HandleShowPanel());
        visitor.add(showReservation);

        // components
        container = getContentPane();
        JLabel welcome = new JLabel("Bienvenue dans l'application de gestion de tournois");
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(welcome);

        //when window closed, exit program
        this.addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent e) {
            formatter.closeConnection();
            System.exit(0);}}
        );

        this.setVisible(true);
    }

    private class HandleShowPanel implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            container.removeAll();
            MainWindow.this.add(getPanel(e));
            MainWindow.this.validate();
        }

        public JPanel getPanel(ActionEvent e) {
            if (e.getSource() == playerInscription) {
                return new ShowNewPerson();
            } else if (e.getSource() == showMatchsTournament) {
                return new ShowMatchsTournament();
            } else if (e.getSource() == matchManagement) {
                return new ShowGestionMatch();
            } else if (e.getSource() == addMatch) {
                return new ShowNewMatch();
            } else if (e.getSource() == showMatchTable) {
                return new ShowAllMatchs();
            } else if (e.getSource() == modifyMatch) {
                return new ShowUpdateMatch();
            } else if (e.getSource() == deleteMatch) {
                return new ShowDeleteMatch();
            } else if (e.getSource() == showPlayer) {
                return new ShowAllPlayers();
            } else if (e.getSource() == showMatchsPlayer) {
                return new ShowMatchsPlayer();
            } else if (e.getSource() == reservation) {
                return new ShowNewReservation();
            } else if (e.getSource() == showReservation) {
                return new ShowReservationsVisitor();
            }
            return null;
        }
    }
}
