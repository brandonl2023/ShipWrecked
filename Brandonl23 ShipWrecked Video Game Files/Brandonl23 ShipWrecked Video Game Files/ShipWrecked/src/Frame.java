import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;

public class Frame extends JFrame {

    public Frame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ShipWrecked");
        setSize(400,400);
        setResizable(false);

        init();
    }

    public void init() {
        setLayout(new GridLayout(1,1,0,0));

        ShipWreckedPanel s = new ShipWreckedPanel();

        add(s);
        setVisible(true);
    }

    public static void main(String[] args) {
        Frame f = new Frame();
    }


}
