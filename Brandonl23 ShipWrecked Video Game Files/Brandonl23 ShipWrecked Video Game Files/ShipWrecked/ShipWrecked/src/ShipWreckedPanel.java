import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ShipWreckedPanel extends JPanel implements ActionListener, KeyListener, MouseListener {

    public static final int PANEL_WIDTH = 1000;
    public static final int PANEL_HEIGHT = 800;

    private Controller c;
    private Timer t;
    private int num;

    public ShipWreckedPanel() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        c = new Controller(this);
        t = new Timer(10, this);
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        t.start();
        num = 5;
    }

    public void actionPerformed(ActionEvent arg0) {
        c.player.update();
        c.update();
        if(c.enemiesKilled == num){
            waveSpawn();
            c.enemiesKilled = 0;
            c.waves++;
        }
        repaint();
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        c.player.draw(g);
        c.draw(g);
        if (!c.player.getAlive()) {
            c.removeEnemy(c.tempEnemy);
            g.clearRect(0,0,getWidth(),getHeight());
            Font dead = new Font("Times New Roman", Font.TYPE1_FONT, 69);
            Font again = new Font("Times New Roman", Font.TYPE1_FONT, 40);
            g.setFont(dead);
            g.drawString("You Died", ShipWreckedPanel.PANEL_WIDTH / 2 - 140, ShipWreckedPanel.PANEL_HEIGHT / 2);
            g.setFont(again);
            g.drawString("PRESS SPACE TO PLAY AGAIN", ShipWreckedPanel.PANEL_WIDTH / 2 - 270, ShipWreckedPanel.PANEL_HEIGHT - 100);

        }
    }
    //Code for spawning in the enemies at random locations outside of the screen
    public void waveSpawn() {
        if(c.waves % 2 == 0){
            num +=2;
        }
        for(int i = 0; i < num; i++){
            int randX = (int) (Math.random() * PANEL_WIDTH) + 100;
            int randY = (int) (Math.random() * PANEL_HEIGHT) + 100;
            if (randX >= PANEL_WIDTH / 2 && randX <= PANEL_WIDTH) {
                randX += PANEL_WIDTH - randX;
            } else {
                randX -= PANEL_WIDTH - randX;
            }
            if (randY >= PANEL_HEIGHT / 2 && randY <= PANEL_HEIGHT) {
                randY -= PANEL_HEIGHT - randY;
            } else {
                randY -= PANEL_HEIGHT - randY;
            }
            c.addEnemy(new Enemy(randX, randY, 20, 20));
        }
    }

    // Keys and mouse inputs
    public void keyPressed(KeyEvent k) {
        int key = k.getKeyCode();
        if (c.player.getAlive()) {
            if (key == KeyEvent.VK_D) {
                c.player.setDx(1);
                c.player.direction = "right";
            } else if (key == KeyEvent.VK_S) {
                c.player.setDy(1);
                c.player.direction = "down";
            } else if (key == KeyEvent.VK_A) {
                c.player.setDx(-1);
                c.player.direction = "left";
            } else if (key == KeyEvent.VK_W) {
                c.player.setDy(-1);
                c.player.direction = "up";
            }

            if (key == KeyEvent.VK_SPACE) {
                if (c.player.getAlive()) {
                    if(c.waves == 0){
                        waveSpawn();
                        c.waves++;
                    }
                }
            }
        }
        else{
            if(key == KeyEvent.VK_SPACE){
                c.player.setAlive(true);
                c.player.setHp(50);
                c.player.x = PANEL_WIDTH/2;
                c.player.y = PANEL_HEIGHT/2;
                waveSpawn();
                c.waves++;
            }
        }

    }
    //This is required because it stops the player from moving
    public void keyReleased(KeyEvent k) {
        if (c.player.getAlive()) {
            switch (k.getKeyCode()) {
                case KeyEvent.VK_D:
                    c.player.setDx(0);
                    break;
                case KeyEvent.VK_S:
                    c.player.setDy(0);
                    break;
                case KeyEvent.VK_A:
                    c.player.setDx(0);
                    break;
                case KeyEvent.VK_W:
                    c.player.setDy(0);
                    break;
            }
        } else {
            c.player.setDy(0);
            c.player.setDx(0);
        }
    }

    public void keyTyped(KeyEvent arg0) {

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }
    //adds the bullets
    public void mousePressed(MouseEvent e) {
        if (c.player.getAlive()) {
            c.addBullet(new Bullet(c.player.getX(), c.player.getY(), e.getX(), e.getY()));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
