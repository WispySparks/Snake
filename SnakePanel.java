package SnakeGame;
import SnakeGame.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakePanel extends JPanel implements KeyListener{

    final int tileSize = 25;
    final int gridSize = 30;
    final int windowSize = (gridSize*tileSize);
    int bodyParts = 6;
    int[] snakeXArray = new int[bodyParts];
    int[] snakeYArray = new int[bodyParts];
    Random rand = new Random();   //random num gen for apple
    int appleX = 0;
    int appleY = 0;
    int direction = 3;    // 0 up, 1 right, 2 down, 3 left, like NESW
    int timerDelay = 100;
    Timer timer;

    SnakePanel() {
        getApple();
        for (int i=0; i<bodyParts; i++) {
            snakeXArray[i] = 1;
            snakeYArray[i] = 1;
        }
        this.setPreferredSize(new Dimension(windowSize, windowSize));
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("im bad");;
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    direction = 0;
                    System.out.println("shieesh");;
                }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("shieesh");;
                    direction = 1;
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println("shieesh");;
                    direction = 2;
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("shieesh");;
                    direction = 3;
                }
            }
            public void keyReleased(KeyEvent e) {

            }
            public void keyTyped(KeyEvent e) {

            }
        });
        //timer.start();
    }

    public void paint(Graphics g) {
        //draw snake
        for (int i=0; i<bodyParts; i++) {
            if (i == 0) {
                g.setColor(new Color(81, 222, 0));
                g.fillRect(150+(i*25), 150, tileSize, tileSize);  
            }
            else {
                g.setColor(new Color(72, 196, 0));
                g.fillRect(150+(i*25), 150, tileSize, tileSize);
            }
        }
        //draw apple
        g.setColor(new Color(255, 0, 0));
        g.fillOval(appleX, appleY, tileSize, tileSize);
    }

    public void getApple() {
        appleX = rand.nextInt(gridSize) * tileSize;
        appleY = rand.nextInt(gridSize) * tileSize;
    }
    
    public void move() {
        
    }
}