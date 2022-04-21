package View;

import java.awt.*;
import java.util.Random;

public class Ball{

    private Rectangle rectangle;
    private Pong pong;
    private int deltaX;
    private int deltaY;
    private Color color;
    public Ball(Pong pong,int x,int y,int width,int height){
        this.pong = pong;
        this.rectangle = new Rectangle(x,y,width,height);
        //generate a random number between 4 and -4
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        System.out.println(randomNumber);
        if(randomNumber < 1){
            deltaX = 4;
        }
        else{
            deltaX = -4;
        }
        deltaY = 2;

        color = Color.WHITE;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }


    public void move (Pong pong) {
        Ball ball = pong.getBall();
        ball.getRectangle().x = ball.getDeltaX() + ball.getRectangle().x;
        ball.getRectangle().y = ball.getDeltaY() + ball.getRectangle().y;
        for( Wall wall : pong.getHorizontalWall()){
            if(wall.collision(this)){
                deltaY = -deltaY;
            }

        }
        for( Wall wall : pong.getVerticalWall()){
            if(wall.collision(this)) {
                pong.removeBall();
            }
        }
        for(Wall player : pong.getPlayers()){
            if(player.collision(this)){
                deltaX= -deltaX;
            }
        }
    }
    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(rectangle.x,rectangle.y, rectangle.width, rectangle.height);
    }

}