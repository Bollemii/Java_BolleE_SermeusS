package View;

public class MovementThread extends Thread{

    private Pong pong;
    public MovementThread(Pong billiard){
        this.pong = billiard;
    }

    public void run(){
        while(true && pong.getBall() != null){
            pong.getBall().move(pong);
            pong.movePlayer();
            try{
                Thread.sleep(10);
            }catch (InterruptedException io){
                io.printStackTrace();
            }
            pong.repaint();
        }
    }

}

