package View;

import javax.swing.*;
import java.awt.*;

public class MatchAnimationWindow extends JFrame {
    private final static int WIDTH = 500;
    private final static int HEIGHT = 600;
    private JPanel panel;
    private Container container;
    private Pong pong;

    public MatchAnimationWindow() {
        super("Match Animation");
        this.setSize(WIDTH, HEIGHT);
        //exit only this window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        container = this.getContentPane();
        container.add(panel);
        pong = new Pong(WIDTH, HEIGHT);
        container.add(pong);
        this.setVisible(true);
    }


}
