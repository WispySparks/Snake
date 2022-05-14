package SnakeGame;
import SnakeGame.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.DelayQueue;

public class SnakePanel extends JPanel implements KeyListener, ActionListener {

    final int tileSize = 25;
    final int gridSize = 30;
    final int windowSize = (gridSize*tileSize);
    int bodyParts = 40;
    int[] snakeXArray = new int[bodyParts];
    int[] snakeYArray = new int[bodyParts];
    int[] prevSnakeXArray = new int[bodyParts];
    int[] prevSnakeYArray = new int[bodyParts];
    Random rand = new Random();   //random num gen for apple
    int appleX = 0;
    int appleY = 0;
    int direction = 3;    // 0 up, 1 right, 2 down, 3 left, like NESW
    int timerDelay = 100;
    boolean running = true;
    boolean eaten = false;
    boolean gotInput = false;
    int score = 0;
    Timer timer;

    SnakePanel() {
        getApple();
        for (int i=0; i<bodyParts; i++) {
            snakeXArray[i] = (i*tileSize)+(tileSize*13);
            snakeYArray[i] = (tileSize * 13);
        }
        this.setPreferredSize(new Dimension(windowSize, windowSize));
        timer = new Timer(timerDelay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        //draw apple
        if (eaten == false) {
            g.setColor(new Color(255, 0, 0));
            g.fillOval(appleX, appleY, tileSize, tileSize);
        }
        else {
            g.setColor(new Color(0, 0, 0));
            g.fillOval(appleX, appleY, tileSize, tileSize);
        }
        //draw snake
        for (int i=0; i<bodyParts; i++) {
            g.setColor(new Color(0, 0, 0));
            g.fillRect(prevSnakeXArray[i], prevSnakeYArray[i], tileSize, tileSize);
            if (i == 0) {
                g.setColor(new Color(81, 222, 0));
                g.fillRect(snakeXArray[i], snakeYArray[i], tileSize, tileSize);  
            }
            else {
                g.setColor(new Color(72, 196, i*5));
                g.fillRect(snakeXArray[i], snakeYArray[i], tileSize, tileSize);
            }
        }
    }

    public void getApple() {
        appleX = rand.nextInt(gridSize) * tileSize;
        appleY = rand.nextInt(gridSize) * tileSize;
        for (int i = 0; i<bodyParts; i++) {
            if (snakeXArray[i] == appleX && snakeYArray[i] == appleY) {
                getApple();
            }
        }
        eaten = false;
    }

    public void checkCollison() {
        // for (int i = 0; i<bodyParts; i++) {
        //     for (int j = 1; i<bodyParts-1; i++) {
        //         if (snakeXArray[i] == snakeXArray[j] && snakeYArray[i] == snakeYArray[j]) {
        //             running = false;
        //         }
        //         if (j == 7) {
        //             break;
        //         }
        //         j++;
        //     }
        // }
        if (snakeXArray[0] == appleX && snakeYArray[0] == appleY) {
            eaten = true;
            score++;
            getApple();   
        }
    }
    
    public void move() {
        switch (direction) {
            case 0:
                System.out.println(direction);
                for (int i = 0; i<bodyParts; i++) {
                    prevSnakeXArray[i] = snakeXArray[i];
                    prevSnakeYArray[i] = snakeYArray[i];
                    if (i == 0) {
                        snakeYArray[i] -= tileSize;
                    }
                    else {
                        snakeXArray[i] = prevSnakeXArray[i-1];
                        snakeYArray[i] = prevSnakeYArray[i-1];
                    }
                }
                break;
            case 1:
                System.out.println(direction);
                for (int i = 0; i<bodyParts; i++) {
                    prevSnakeXArray[i] = snakeXArray[i];
                    prevSnakeYArray[i] = snakeYArray[i];
                    if (i == 0) {
                        snakeXArray[i] += tileSize;
                    }
                    else {
                        snakeXArray[i] = prevSnakeXArray[i-1];
                        snakeYArray[i] = prevSnakeYArray[i-1];
                    }
                }
                break;
            case 2:
                System.out.println(direction);
                for (int i = 0; i<bodyParts; i++) {
                    prevSnakeXArray[i] = snakeXArray[i];
                    prevSnakeYArray[i] = snakeYArray[i];
                    if (i == 0) {
                        snakeYArray[i] += tileSize;
                    }
                    else {
                        snakeXArray[i] = prevSnakeXArray[i-1];
                        snakeYArray[i] = prevSnakeYArray[i-1];
                    }
                }
                break;
            case 3:
                System.out.println(direction);
                for (int i = 0; i<bodyParts; i++) {
                    prevSnakeXArray[i] = snakeXArray[i];
                    prevSnakeYArray[i] = snakeYArray[i];
                    if (i == 0) {
                        snakeXArray[i] -= tileSize;
                    }
                    else {
                        snakeXArray[i] = prevSnakeXArray[i-1];
                        snakeYArray[i] = prevSnakeYArray[i-1];
                    }
                }
                break;
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        checkCollison();
        if (running) {
            move();
        }
        else {
            timer.stop();
        }
        gotInput = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*for (int i=0; i<bodyParts; i++) {
            System.out.println(snakeXArray[i] + " X Cor " + i);
            System.out.println(snakeYArray[i] + " Y Cor " + i);
        }*/
        if (e.getKeyCode() == KeyEvent.VK_UP && direction != 2 && gotInput == false) {
            //System.out.println("up");
            direction = 0;
            gotInput = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && direction != 3 && gotInput == false) {
            //System.out.println("right");
            direction = 1;
            gotInput = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && direction != 0 && gotInput == false) {
            //System.out.println("down");
            direction = 2;
            gotInput = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && direction != 1 && gotInput == false) {
            //System.out.println("left");
            direction = 3;
            gotInput = true;
        }
    }
    public void keyReleased(KeyEvent event) {

    }
    public void keyTyped(KeyEvent event) {

    }
}