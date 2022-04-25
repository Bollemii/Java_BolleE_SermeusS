package View;

import java.awt.*;

public class Wall {
    private Rectangle rectangle;

    public Wall(int x, int y, int width, int height) {
        this.rectangle = new Rectangle(x, y, width, height);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void draw(Graphics g, Color color){
        g.setColor(color);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public boolean collision(Ball ball){
        return rectangle.intersects(ball.getRectangle());
    }
}
