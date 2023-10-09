import java.awt.*;
import java.util.ArrayList;

public class Controller {

    private ArrayList<Bullet> b = new ArrayList<>();
    private ArrayList<Enemy> e = new ArrayList<>();
    private ArrayList<Apple> p = new ArrayList<>();

    Bullet tempBullet;
    Enemy tempEnemy;
    ShipWreckedPanel panel;
    Player player;
    Apple apple;
    int enemiesKilled;
    int totalVanqs;
    int waves;

    public Controller(ShipWreckedPanel panel) {
        this.panel = panel;
        player = new Player(ShipWreckedPanel.PANEL_WIDTH / 2, ShipWreckedPanel.PANEL_HEIGHT / 2, 30, 30);
        enemiesKilled = 0;
    }

    public void update() {
        if (player.getAlive()) {
            for (int i = 0; i < b.size(); i++) {
                tempBullet = b.get(i);
                if (tempBullet.getY() < 0 || tempBullet.getY() > panel.getHeight()) {  //Removes bullet when it goes above or below screen
                    removeBullet(tempBullet);
                    //System.out.println("Off screen");
                } else if (tempBullet.getX() <= 0 || tempBullet.getX() > panel.getWidth()) { //removes bullet when goes left or right off-screen
                    removeBullet(tempBullet);
                    //System.out.println("Off screen");
                } else {
                    for (int j = 0; j < e.size(); j++) {
                        if (tempBullet.intersects(e.get(j))) {
                            StdAudio.playInBackground("pop.wav");
                            enemiesKilled++;
                            totalVanqs++;
                            if (Math.random() <= 0.1) {
                                addApple(new Apple(e.get(j).getX(), e.get(j).getY(), 20, 20));
                            }
                            removeEnemy(e.get(j));
                            removeBullet(tempBullet);
                            //System.out.println("Hit");
                        }
                    }
                    tempBullet.update();
                }
            }
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).intersects(player)) {
                    System.out.println("Player was hit");
                    if (Math.random() <= 0.1) {
                        addApple(new Apple(e.get(i).getX(), e.get(i).getY(), 20, 20));
                    }
                    else{
                        //System.out.println("Nothing was dropped");
                    }
                    removeEnemy(e.get(i));
                    enemiesKilled++;
                    player.setHp(player.getHp() - 10);
                    System.out.println(player.getHp());
                    if (player.getHp() <= 0) {
                        player.setAlive(false);
                        System.out.println("Wasted");
                    }
                }
            }
            for(int i = 0; i<p.size(); i++){
                if(player.intersects(p.get(i))){
                    //System.out.println(player.getHp());
                    if(player.getHp() < 50){
                        player.setHp(player.getHp() + 10);
                        //System.out.println("Player hp is: " + player.getHp());
                    }
                    StdAudio.playInBackground("crunch.wav");
                    removeApple(p.get(i));
                }
            }

            for (int i = 0; i < e.size(); i++) {
                tempEnemy = e.get(i);
                tempEnemy.update(player.getX(), player.getY());
            }
        } else {
            totalVanqs = 0;
            enemiesKilled = 0;
            waves = 0;
        }
    }

    public void draw(Graphics g) {
        player.draw(g);
        Font enemies = new Font("Open Sans", Font.TYPE1_FONT, 30);
        g.setFont(enemies);
        g.drawString("Vanquishes: " + totalVanqs, 10, 50);
        g.drawString("Wave: " + waves, ShipWreckedPanel.PANEL_WIDTH - 200, 50);
        for (int i = 0; i < b.size(); i++) {
            tempBullet = b.get(i);

            tempBullet.draw(g);
        }
        for (int i = 0; i < e.size(); i++) {
            tempEnemy = e.get(i);

            tempEnemy.draw(g);
        }
        for(int i = 0; i < p.size(); i++){
            apple = p.get(i);

            apple.draw(g);

        }

    }

    public void addApple(Apple block) {
        p.add(block);
    }

    public void removeApple(Apple block) {
        p.remove(block);
    }

    public void addBullet(Bullet block) {
        b.add(block);
    }

    public void removeBullet(Bullet block) {
        b.remove(block);
    }

    public void addEnemy(Enemy block) {
        e.add(block);
    }

    public void removeEnemy(Enemy block) {
        e.remove(block);
    }

}
