package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pong extends JPanel {
	private final static int WALL_THICK = 10;
	private final static int PLAYER_HEIGHT = 100;
	private List<Wall> verticalWall;
	private List<Wall> horizontalWall;
	private List<Wall> players;
	private Ball ball;
	private int frameWidth;
	private int frameHeight;
	private MovementThread moveBall;

	public Pong(int width, int height){
		frameWidth = width - 14;
		frameHeight = height - 37;
		this.setBackground(Color.BLACK);

		ArrayList<Wall> verticalArray = new ArrayList<>();
		verticalWall = Collections.synchronizedList(verticalArray);
		ArrayList<Wall> horizontalArray = new ArrayList<>();
		horizontalWall = Collections.synchronizedList(horizontalArray);
		ArrayList<Wall> playersArray = new ArrayList<>();
		players = Collections.synchronizedList(playersArray);

		players.add(new Wall(2*WALL_THICK,frameHeight-2*WALL_THICK-PLAYER_HEIGHT, WALL_THICK, PLAYER_HEIGHT));
		players.add(new Wall(frameWidth-3*WALL_THICK,2*WALL_THICK, WALL_THICK, PLAYER_HEIGHT));

		horizontalWall.add(new Wall(0, 0, frameWidth, WALL_THICK));
		horizontalWall.add(new Wall(0, frameHeight-WALL_THICK, frameWidth, WALL_THICK));
		verticalWall.add(new Wall(0, 0, WALL_THICK, frameHeight));
		verticalWall.add(new Wall(frameWidth-WALL_THICK, 0, WALL_THICK, frameHeight));

		ball = new Ball(frameWidth/2, frameHeight/2);

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

	public Wall getPlayer(int i) {return players.get(i);}

	public void paint (Graphics g) {
		super.paint(g);
		if (ball != null) {
			ball.draw(g);
			for (Wall wall : verticalWall) {
				wall.draw(g, Color.BLACK);
			}

			for (Wall wall : horizontalWall) {
				wall.draw(g, Color.BLACK);
			}

			for (Wall player : players) {
				player.draw(g, Color.white);
			}
		}
	}

	public void movePlayer() {
		if(ball != null) {
			Rectangle rectanglePlayer = ball.getDeltaX() < 0 ? getPlayer(0).getRectangle():getPlayer(1).getRectangle();
			if (rectanglePlayer.y + PLAYER_HEIGHT/2 < ball.getRectangle().y) {
				rectanglePlayer.y += 4;
			} else {
				rectanglePlayer.y -= 4;
			}
		}
	}

	public void removeBall() {
		ball = null;
		ball = new Ball(frameWidth/2,frameHeight/2);
	}
}
