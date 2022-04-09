package View;

import javax.swing.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu appMenu;
    private JMenuItem exit;
    private JMenu tournament;
    private JMenuItem playerInscription, matchManagement;
    private JMenu match;
    private JMenuItem addMatch,showMatchTable,showAllMatchInfo,ModifyMatch,deleteMatch;
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
        tournament.add(playerInscription);
        matchManagement = new JMenuItem("Gestion des matchs");
        tournament.add(matchManagement);
        match = new JMenu("Match");
        match.setMnemonic('M');
        menuBar.add(match);
        addMatch = new JMenuItem("Ajouter match");
        match.add(addMatch);
        showMatchTable = new JMenuItem("Afficher tableau des matchs");
        match.add(showMatchTable);
        showAllMatchInfo = new JMenuItem("Afficher toutes les informations des matchs");
        match.add(showAllMatchInfo);
        ModifyMatch = new JMenuItem("Modifier match");
        match.add(ModifyMatch);
        deleteMatch = new JMenuItem("Supprimer match");
        match.add(deleteMatch);
        player = new JMenu("Joueur");
        player.setMnemonic('J');
        menuBar.add(player);
        showPlayer = new JMenuItem("Afficher joueur");
        player.add(showPlayer);
        visitor = new JMenu("Visiteur");
        visitor.setMnemonic('V');
        menuBar.add(visitor);
        reservation = new JMenuItem("Reservation");
        visitor.add(reservation);
        JLabel welcome = new JLabel("Bienvenue dans l'application de gestion de tournoi");
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcome);

        //on click on ShowPlayer in menu bar
        showPlayer.addActionListener(new HandleShowPlayer());
        //when window closed, exit
        this.addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent e) {System.exit(0);}});

        this.setVisible(true);
    }
    //generate private class HandleShowPlayer
    private class HandleShowPlayer implements ActionListener {
        //extends action listener
            //method to handle action
            public void actionPerformed(ActionEvent e) {
                //clear the window
                getContentPane().removeAll();
                ShowPlayer showPlayer = new ShowPlayer();
                MainWindow.this.add(showPlayer);
                showPlayer.setVisible(true);
                MainWindow.this.validate();
            }
        }

}
