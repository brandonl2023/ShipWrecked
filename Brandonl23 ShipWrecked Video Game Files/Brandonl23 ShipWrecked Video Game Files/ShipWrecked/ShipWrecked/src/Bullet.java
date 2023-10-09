import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Bullet extends Rectangle2D.Double {

    private final int speed;

    private double velocityX;
    private double velocityY;
    private double length;

    //Tracks player using Pythagorean theorem
    public Bullet(double x, double y, double endX, double endY) {
        super(x, y, 4, 2);
        speed = 5;
        velocityX = endX - x;
        velocityY = endY - y;
        length = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
        velocityX *= speed / length;
        velocityY *= speed / length;

    }
    //New coordinates to track player
    public void update() {
        x += velocityX;
        y += velocityY;

    }

    public void draw(Graphics g) {
        g.fillRect((int) this.x, (int) this.y, 4, 2);
    }

}
