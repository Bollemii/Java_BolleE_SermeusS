package View;

import javax.swing.*;
import java.awt.*;

public class MatchAnimationWindow extends JFrame {
    private JPanel panel;
    private Container container;
    private Pong pong;

    public MatchAnimationWindow() {
        super("Match Animation");
        setSize(1040, 860);
        //exit only this window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        panel = new JPanel();
        container = this.getContentPane();
        container.add(panel);
        pong = new Pong(new Wall(0,0,20,800),new Wall(1000,0,20,820),new Wall(0,0,1000,20),new Wall(0,800,1000,20));
        container.add(pong);
        setVisible(true);
    }


}
