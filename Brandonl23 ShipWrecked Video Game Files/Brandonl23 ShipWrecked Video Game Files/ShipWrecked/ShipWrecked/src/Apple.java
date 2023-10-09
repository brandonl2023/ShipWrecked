import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Apple extends Rectangle2D.Double {

    public BufferedImage apple;

    public Apple(double x, double y, double width, double height) {
        super(x,y,width,height);
        getPlayerImage();
    }

    public void draw(Graphics g) {
        BufferedImage image = apple;
        g.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
    }

    public void getPlayerImage() {
        try {
            apple = ImageIO.read(getClass().getResourceAsStream("apple.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
