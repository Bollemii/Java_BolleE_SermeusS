package View;

import java.awt.*;
import java.util.Random;

public class Ball{
	private final static int SIZE = 10;
	private final static int SPEED = 2;
	private Rectangle rectangle;
	private int deltaX;
	private int deltaY;
	private Color color;

	public Ball(int x, int y) {
		this.rectangle = new Rectangle(x, y, SIZE, SIZE);

		Random random = new Random();
		int randomNumber = random.nextInt(2);

		if (randomNumber < 1) {
			deltaX = SPEED;
		} else {
			deltaX = -SPEED;
		}
		deltaY = SPEED;

		color = Color.WHITE;
	}

	public int getDeltaX() {
		return deltaX;
	}
	public int getDeltaY() {
		return deltaY;
	}
	public Rectangle getRectangle() {
		return rectangle;
	}

	public void move (Pong pong) {
		Ball ball = pong.getBall();

		ball.getRectangle().x += ball.getDeltaX();
		ball.getRectangle().y += ball.getDeltaY();

		for (Wall wall : pong.getHorizontalWall()) {
			if (wall.collision(this)) {
				deltaY *= -1;
			}
		}
		for (Wall wall : pong.getVerticalWall()) {
			if (wall.collision(this)) {
				pong.removeBall();
			}
		}
		for (Wall player : pong.getPlayers()) {
			if (player.collision(this)) {
				deltaX *= -1;
			}
		}
	}

	public void draw(Graphics g){
		g.setColor(color);
		g.fillOval(rectangle.x,rectangle.y, rectangle.width, rectangle.height);
	}
}