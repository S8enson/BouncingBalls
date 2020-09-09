/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BallGUI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JPanel;

/**
 *
 * @author dcm2548
 */
public class Ball implements Runnable {

    Thread t;
    int x, y, dx, dy, radius;
    static int worldHeight, worldWidth;
    Color color;
    private Random random;

    public Ball(int worldHeight, int worldWidth) {
        random = new Random();
        this.worldHeight = worldHeight;
        this.worldWidth = worldWidth;
        this.x = ThreadLocalRandom.current().nextInt(worldWidth / 2 - 50, worldWidth / 2 + 50 + 1);
        this.y = ThreadLocalRandom.current().nextInt(worldHeight / 2 - 50, worldHeight / 2 + 50 + 1);
        this.dx = ThreadLocalRandom.current().nextInt(-5, 5 + 1);
        this.dy = ThreadLocalRandom.current().nextInt(-5, 5 + 1);
        this.radius = ThreadLocalRandom.current().nextInt(10, 50 + 1);
        this.color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
        t = new Thread(this);
        t.start();

    }

    @Override
    public void run() {
        while (t.isAlive()) {

            move();
            try {
                t.sleep(10);
            } catch (Exception e) {
            }

        }
    }

    public void move() {

        if (x >= worldWidth - radius) {
            x = worldWidth - radius;
            dx = -dx;
        } else if (x <= 0 + radius) {
            x = 0 + radius;
            dx = -dx;
        }
        if (y >= worldHeight - radius) {
            y = worldHeight - radius;
            dy = -dy;
        } else if (y <= 0 + radius) {
            y = 0 + radius;
            dy = -dy;
        }

        x += dx;
        y += dy;

    }

    public void drawBall(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);

    }

    public void requestStop() {
        t.stop();
    }
}
