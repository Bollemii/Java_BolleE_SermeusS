package View.Pong;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pong extends JPanel {
	private final static int WIDTH = 225;
	private final static int HEIGHT = 300;
	private final static int WALL_THICK = 10;
	private final static int PLAYER_HEIGHT = 70;
	private final static int PLAYER_SPEED = 2;
	private List<Wall> verticalWall;
	private List<Wall> horizontalWall;
	private List<Wall> players;
	private Ball ball;
	private MovementThread moveBall;

	public Pong() {
		this.setBackground(Color.BLACK);

		ArrayList<Wall> verticalArray = new ArrayList<>();
		verticalWall = Collections.synchronizedList(verticalArray);
		ArrayList<Wall> horizontalArray = new ArrayList<>();
		horizontalWall = Collections.synchronizedList(horizontalArray);
		ArrayList<Wall> playersArray = new ArrayList<>();
		players = Collections.synchronizedList(playersArray);

		players.add(new Wall(2 * WALL_THICK, HEIGHT - 2 * WALL_THICK - PLAYER_HEIGHT, WALL_THICK, PLAYER_HEIGHT));
		players.add(new Wall(WIDTH - 3 * WALL_THICK, 2 * WALL_THICK, WALL_THICK, PLAYER_HEIGHT));

		horizontalWall.add(new Wall(0, 0, WIDTH, WALL_THICK));
		horizontalWall.add(new Wall(0, HEIGHT - WALL_THICK, WIDTH, WALL_THICK));
		verticalWall.add(new Wall(0, 0, WALL_THICK, HEIGHT));
		verticalWall.add(new Wall(WIDTH - WALL_THICK, 0, WALL_THICK, HEIGHT));

		ball = new Ball(WIDTH / 2, HEIGHT / 2);

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
				rectanglePlayer.y += PLAYER_SPEED;
			} else {
				rectanglePlayer.y -= PLAYER_SPEED;
			}
		}
	}

	public void removeBall() {
		ball = null;
		ball = new Ball(WIDTH /2, HEIGHT /2);
	}
}
