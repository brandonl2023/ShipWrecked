import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Enemy extends Rectangle2D.Double {

    private final int speed;

    private double velocityX, velocityY;
    private double length;

    public Enemy(double x, double y, double width, double height){
        super(x,y,width,height);
        speed = (int) (Math.random() * 3)+ 1;

    }
    //Code for Enemy that is basically Pythagorean theorem
    public void update(double playerX, double playerY){
        velocityX = playerX - x;
        velocityY = playerY - y;
        length = Math.sqrt(velocityX * velocityX + velocityY * velocityY);
        velocityX *= speed / length;
        velocityY *= speed / length;
        x += velocityX;
        y += velocityY;
    }

    public void draw(Graphics g){
        g.fillRect((int) this.x, (int) this.y, (int) this.width,(int) this.height);
    }

}
