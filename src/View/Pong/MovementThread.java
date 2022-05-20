package View.Pong;

import javax.swing.*;

public class MovementThread extends Thread{
    private Pong pong;

    public MovementThread(Pong pong){
        this.pong = pong;
    }

    public void run() {
        while(pong.getBall() != null) {
            try{
                Thread.sleep(10);
                pong.getBall().move(pong);
                pong.movePlayer();
                pong.repaint();
            } catch (InterruptedException exception){
                JOptionPane.showMessageDialog(
                        null,
                        "Problème du thread",
                        "Erreur thread",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}

