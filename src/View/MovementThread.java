package View;

public class MovementThread extends Thread{
    private Pong pong;

    public MovementThread(Pong pong){
        this.pong = pong;
    }

    public void run(){
        while(true && pong.getBall() != null){
            pong.getBall().move(pong);
            pong.movePlayer();
            try{
                Thread.sleep(10);
            }catch (InterruptedException exception){
                exception.printStackTrace();
            }
            pong.repaint();
        }
    }
}

