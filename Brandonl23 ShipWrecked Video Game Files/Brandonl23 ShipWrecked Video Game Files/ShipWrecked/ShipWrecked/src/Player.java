import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Rectangle2D.Double {

    private int dx, dy, hp, spriteCounter, spriteNum;
    private boolean isAlive;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.dx = 0;
        this.dy = 0;
        hp = 50;
        isAlive = true;
        getPlayerImage();
        direction = "down";
        spriteCounter = 0;
        spriteNum = 1;
    }

    public void update() { //updates player
        this.x += dx;
        this.y += dy;
        spriteCounter++;
        if (spriteCounter > 22) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics g) {
        BufferedImage image = null;
        if (direction == "up") {
            if (spriteNum == 1) {
                image = up1;
            }
            if (spriteNum == 2) {
                image = up2;
            }

        } else if (direction == "down") {
            if (spriteNum == 1) {
                image = down1;
            }
            if (spriteNum == 2) {
                image = down2;
            }

        } else if (direction == "left") {
            if (spriteNum == 1) {
                image = left1;
            }
            if (spriteNum == 2) {
                image = left2;
            }
        } else {
            if (spriteNum == 1) {
                image = right1;
            }
            if (spriteNum == 2) {
                image = right2;
            }
        }
        if (isAlive) {
            g.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
            if (hp == 50) {
                g.setColor(Color.GREEN);
                g.fillRect((int) this.x - 2, (int) this.y - 5, (int) this.width + 4, 3);
            } else if (hp == 40) {
                g.setColor(Color.GREEN);
                g.fillRect((int) this.x - 2, (int) this.y - 5, (int) this.width - 5, 3);
            } else if (hp == 30) {
                g.setColor(Color.YELLOW);
                g.fillRect((int) this.x - 2, (int) this.y - 5, (int) this.width - 15, 3);
            } else if (hp == 20) {
                g.setColor(Color.red);
                g.fillRect((int) this.x - 2, (int) this.y - 5, (int) this.width - 20, 3);
            } else if (hp == 10) {
                g.setColor(Color.red);
                g.fillRect((int) this.x - 2, (int) this.y - 5, (int) this.width - 25, 3);
            } else if (hp == 0){
                g.clearRect(0, 0, (int) width, (int) height);
            }
            g.setColor(Color.black);
        }
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void setAlive(boolean status) {
        isAlive = status;
    }

    public boolean getAlive() {
        return isAlive;
    }

}
