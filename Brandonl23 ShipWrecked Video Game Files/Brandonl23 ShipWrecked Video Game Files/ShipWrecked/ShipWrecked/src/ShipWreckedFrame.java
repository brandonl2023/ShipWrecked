import javax.swing.*;
import java.awt.*;

public class ShipWreckedFrame extends JFrame {

    public ShipWreckedFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ShipWrecked");
        setResizable(false);
        ShipWreckedPanel s = new ShipWreckedPanel();
        add(s);
        this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width / 2 - getWidth() / 2;
        int y = screenSize.height / 2 - getHeight() / 2;
        this.setLocation(x, y); // positions the game window in center of screen
    }

}
