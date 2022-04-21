package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pong extends JPanel {
    private ArrayList<Wall> verticalArray;
    private List<Wall> verticalWall;
    private ArrayList<Wall>horizontalArray;
    private List<Wall>horizontalWall;

    private  List<Wall>players;
    private ArrayList<Wall> playersArray;
    private Ball ball;
    private int totalPoints;
    private MovementThread moveBall;

    public Pong(Wall verticalwall1, Wall verticalwall2, Wall horizontalWall1, Wall horizontalWall2){
        this.setBackground(Color.BLACK);
        verticalArray = new ArrayList<>();
        verticalWall = Collections.synchronizedList(verticalArray);
        horizontalArray = new ArrayList<>();
        horizontalWall = Collections.synchronizedList(horizontalArray);
        playersArray = new ArrayList<>();
        players = Collections.synchronizedList(playersArray);
        players.add(new Wall(40,300,20,100));
        players.add(new Wall(900,300,20,100));
        verticalWall.add(verticalwall1);
        verticalWall.add(verticalwall2);
        horizontalWall.add(horizontalWall1);
        horizontalWall.add(horizontalWall2);
        ball = new Ball(this,450,350,10,10);
        totalPoints = 0;
        moveBall = new MovementThread(this);
        moveBall.start();
    }

    public Ball getBall() {
        return ball;
    }

    public List<Wall> getPlayers(){
        return players;
    }

    public List<Wall> getVerticalWall() {
        return verticalWall;
    }

    public List<Wall> getHorizontalWall() {
        return horizontalWall;
    }
    public Wall getPlayer(int i){return players.get(i);}
    public void paint (Graphics g) {
        super.paint(g);
        if (ball != null) {
            ball.draw(g);
        for (int i = 0; i < verticalWall.size(); i++) {
            verticalWall.get(i).draw(g, Color.BLACK);
        }

        for (int i = 0; i < horizontalWall.size(); i++) {
            horizontalWall.get(i).draw(g, Color.BLACK);
        }

        for (int i = 0; i < players.size(); i++) {
            players.get(i).draw(g, Color.white);
        }
    }

    }
    //remove the ball from the game

    public void movePlayer(){
        if(ball != null) {
            Rectangle rectanglePlayer = ball.getRectangle().x < 450 ? getPlayer(0).getRectangle():getPlayer(1).getRectangle();
            if(rectanglePlayer.y < ball.getRectangle().y){
                rectanglePlayer.y += 3;
            }else{
                rectanglePlayer.y -=3;
            }
        }
    }
    public void removeBall(){
        ball = null;
        ball = new Ball(this,450,350,10,10);
    }
}
