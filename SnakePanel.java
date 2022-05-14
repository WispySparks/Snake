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
    int bodyParts = 5;
    int arraySize = 100;
    int[] snakeXArray = new int[arraySize];
    int[] snakeYArray = new int[arraySize];
    int[] prevSnakeXArray = new int[arraySize];
    int[] prevSnakeYArray = new int[arraySize];
    Random rand = new Random();   // random num gen for apple
    int appleX = 0;
    int appleY = 0;
    int direction = 3;    // 0 up, 1 right, 2 down, 3 left, like NESW
    int timerDelay = 250;   // basically how fast the game is
    boolean running = true;    // if you have lost yet
    boolean eaten = false;
    boolean gotInput = false;     //bug fix for going inside yourself
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

    public void paint(Graphics g) {     // redraws window
        // draw apple
        //ToDo blink snake when dead, input not feeling great should implment a next move
        if (eaten == false) {
            g.setColor(new Color(255, 0, 0));
            g.fillOval(appleX, appleY, tileSize, tileSize);
        }
        else {
            g.setColor(new Color(0, 0, 0));
            g.fillOval(appleX, appleY, tileSize, tileSize);
        }
        // draw snake
        for (int i=0; i<bodyParts; i++) {
            g.setColor(new Color(0, 0, 0));
            g.fillRect(prevSnakeXArray[i], prevSnakeYArray[i], tileSize, tileSize);
            if (i == 0) {
                g.setColor(new Color(0, 255, 0));
                g.fillRect(snakeXArray[i], snakeYArray[i], tileSize, tileSize);
            }
            else {
                g.setColor(new Color(0, 130, 0));
                g.fillRect(snakeXArray[i], snakeYArray[i], tileSize, tileSize);
            }
        }
    }

    public void getApple() {    // get new cords of apple
        appleX = rand.nextInt(gridSize) * tileSize;
        appleY = rand.nextInt(gridSize) * tileSize;
        for (int i = 0; i<bodyParts; i++) {
            if (snakeXArray[i] == appleX && snakeYArray[i] == appleY) {
                getApple();
            }
        }
        eaten = false;
    }

    public void checkCollison() {   // check all collisions for the snake
        for (int j = 1; j<bodyParts; j++) {
            if (snakeXArray[0] == snakeXArray[j] && snakeYArray[0] == snakeYArray[j]) {
                running = false;
            }
        }
        for (int i = 0; i<bodyParts; i++) {
            if (snakeXArray[i] < 0 || snakeXArray[i] >= windowSize || snakeYArray[i] < 0 || snakeYArray[i] >= windowSize) {
                running = false;
            }
        }
        if (snakeXArray[0] == appleX && snakeYArray[0] == appleY) {
            eaten = true;
            if (timerDelay <= 50) {

            }
            else {
                timerDelay -= 10;
                timer.setDelay(timerDelay);    
            }
            score++;
            bodyParts++;
            System.out.println(timerDelay);
            addBody();
            getApple();   
        }
    }
    
    public void move() {    // move the snake
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
    public void actionPerformed(ActionEvent e) {    // run by the Timer, stops timer when you lose
        checkCollison();
        if (running) {
            move();
        }
        else {
            timer.stop();
        }
        gotInput = false;
    }

    public void addBody() {
        if (bodyParts == arraySize) {
            running = false;
            System.out.println("Congrats you won!");
        }
        else {
            snakeXArray[bodyParts-1] = prevSnakeXArray[bodyParts-1];
            snakeYArray[bodyParts-1] = prevSnakeYArray[bodyParts-1];
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {    // get key presses of user
        if (e.getKeyCode() == KeyEvent.VK_UP && direction != 2 && gotInput == false) {
            direction = 0;
            gotInput = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && direction != 3 && gotInput == false) {
            direction = 1;
            gotInput = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && direction != 0 && gotInput == false) {
            direction = 2;
            gotInput = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && direction != 1 && gotInput == false) {
            direction = 3;
            gotInput = true;
        }
    }
    public void keyReleased(KeyEvent event) {

    }
    public void keyTyped(KeyEvent event) {

    }
}