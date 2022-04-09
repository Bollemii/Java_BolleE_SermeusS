
package View;


        import View.ShowPlayer;
        import View.ShowTournament;

        import javax.swing.*;
    import java.awt.event.*;

public class MainWindow extends JFrame {
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
        playerInscription.addActionListener(new HandleShowInscription());
        tournament.add(playerInscription);
        matchManagement = new JMenuItem("Gestion des matchs");
        matchManagement.addActionListener(new HandleShowGestionMatch());
        tournament.add(matchManagement);
        match = new JMenu("Match");
        match.setMnemonic('M');
        menuBar.add(match);
        addMatch = new JMenuItem("Ajouter match");
        addMatch.addActionListener(new HandleShowAddMatch());
        match.add(addMatch);
        showMatchTable = new JMenuItem("Afficher tableau des matchs");
        showMatchTable.addActionListener(new HandleShowTournament());
        match.add(showMatchTable);
        showAllMatchInfo = new JMenuItem("Afficher toutes les informations des matchs");
        showAllMatchInfo.addActionListener(new HandleShowAllMatchInfo());
        match.add(showAllMatchInfo);
        modifyMatch = new JMenuItem("Modifier match");
        modifyMatch.addActionListener(new HandleShowModifyMatch());
        match.add(modifyMatch);
        deleteMatch = new JMenuItem("Supprimer match");
        deleteMatch.addActionListener(new HandleShowDeleteMatch());
        match.add(deleteMatch);
        player = new JMenu("Joueur");
        player.setMnemonic('J');
        menuBar.add(player);
        showPlayer = new JMenuItem("Afficher joueur");
        showPlayer.addActionListener(new HandleShowPlayer());
        player.add(showPlayer);

        visitor = new JMenu("Visiteur");
        visitor.setMnemonic('V');
        menuBar.add(visitor);
        reservation = new JMenuItem("Reservation");
        reservation.addActionListener(new HandleShowReservation());
        visitor.add(reservation);
        JLabel welcome = new JLabel("Bienvenue dans l'application de gestion de tournoi");
        welcome.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcome);

        //on click on ShowPlayer in menu bar
        showPlayer.addActionListener(new HandleShowPlayer());
        //when window closed, exit
        this.addWindowListener(new WindowAdapter() {
                                   public void windowClosing(WindowEvent e) {System.exit(0);
                                   }
                               });

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

    private class HandleShowTournament implements ActionListener {
        //extends action listener
        //method to handle action
        public void actionPerformed(ActionEvent e) {
            //clear the window
            getContentPane().removeAll();
            ShowTournament showPlayer = new ShowTournament();
            MainWindow.this.add(showPlayer);
            showPlayer.setVisible(true);
            MainWindow.this.validate();
        }
    }

    private class HandleShowModifyMatch implements ActionListener{
        public void actionPerformed(ActionEvent e){
            getContentPane().removeAll();
            ShowModifyMatch modifyMatch = new ShowModifyMatch();
            MainWindow.this.add(modifyMatch);
            modifyMatch.setVisible(true);
            MainWindow.this.validate();
        }
    }

    private class HandleShowAddMatch implements ActionListener{
        public void actionPerformed(ActionEvent e){
            getContentPane().removeAll();
            ShowAddMatch addMatch = new ShowAddMatch();
            MainWindow.this.add(addMatch);
            addMatch.setVisible(true);
            MainWindow.this.validate();
        }
    }
    private class HandleShowMatchTable implements ActionListener{
        public void actionPerformed(ActionEvent e){
            getContentPane().removeAll();
            ShowMatchTable matchTable = new ShowMatchTable();
            MainWindow.this.add(matchTable);
            matchTable.setVisible(true);
            MainWindow.this.validate();
        }
    }
    private class HandleShowAllMatchInfo implements ActionListener{
        public void actionPerformed(ActionEvent e){
            getContentPane().removeAll();
            ShowAllMatchInfo allMatchInfo = new ShowAllMatchInfo();
            MainWindow.this.add(allMatchInfo);
            allMatchInfo.setVisible(true);
            MainWindow.this.validate();
        }
    }
    private class HandleShowGestionMatch implements ActionListener{
        public void actionPerformed(ActionEvent e){
            getContentPane().removeAll();
            ShowGestionMatch gestionMatch = new ShowGestionMatch();
            MainWindow.this.add(gestionMatch);
            gestionMatch.setVisible(true);
            MainWindow.this.validate();
        }
    }
    private class HandleShowInscription implements ActionListener{
        public void actionPerformed(ActionEvent e){
            getContentPane().removeAll();
            ShowInscription inscription = new ShowInscription();
            MainWindow.this.add(inscription);
            inscription.setVisible(true);
            MainWindow.this.validate();
        }
    }
    private class HandleShowReservation implements ActionListener{
        public void actionPerformed(ActionEvent e){
            getContentPane().removeAll();
            ShowReservation reservation = new ShowReservation();
            MainWindow.this.add(reservation);
            reservation.setVisible(true);
            MainWindow.this.validate();
        }
    }
    private class HandleShowDeleteMatch implements ActionListener{
        public void actionPerformed(ActionEvent e){
            getContentPane().removeAll();
            ShowDeleteMatch deleteMatch = new ShowDeleteMatch();
            MainWindow.this.add(deleteMatch);
            deleteMatch.setVisible(true);
            MainWindow.this.validate();
        }
    }
}
