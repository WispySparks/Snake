package src.Snake;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.io.File;
import javax.sound.sampled.*;

public class SnakePanel extends JPanel implements KeyListener, ActionListener {

    private final int tileSize = 25;
    private final int gridSize = 30;
    private final int windowSize = (gridSize*tileSize);
    private final int arraySize = 100;
    private final File appleSound = new File("src/Snake/AppleSound.wav");
    private final File deathSound = new File("src/Snake/DeathSound.wav");
    private int bodyParts;
    private int[] snakeXArray = new int[arraySize];
    private int[] snakeYArray = new int[arraySize];
    private int[] prevSnakeXArray = new int[arraySize];
    private int[] prevSnakeYArray = new int[arraySize];
    private Random rand = new Random();   // random num gen for apple
    private int appleX = 0;
    private int appleY = 0;
    private int direction;    // 0 up, 1 right, 2 down, 3 left, like NESW
    private double timerDelay = 250;   // basically how fast the game is
    private boolean running;    // if you have lost yet
    private boolean eaten = false;   // if apple eaten
    private boolean gotInput;     // bug fix for going inside yourself
    private boolean blinkBlack = true;   // for blinking when you die
    private int blinkAmount;
    private int score;
    private boolean won;
    private int wins = 0;
    private double time;
    private Timer timer;
    private JLabel display = new JLabel();
    AudioInputStream audioStream;
    Clip clip;

    SnakePanel() {  //ToDo input not feeling great should implment a next move
        this.setPreferredSize(new Dimension(windowSize, windowSize));
        this.setBackground(Color.black);
        this.setFocusable(true);
        display.setForeground(Color.RED);
        display.setFont(new Font("Serif", Font.PLAIN, 40));
        this.add(display);
        timer = new Timer((int) timerDelay, this);
        setup();
    }

    public void paint(Graphics g) {     // redraws window
        super.paint(g);     // draws the background
        // draw apple
        if (eaten == false) {
            g.setColor(new Color(255, 0, 0));
            g.fillOval(appleX, appleY, tileSize, tileSize);
        }
        else {
            g.setColor(new Color(0, 0, 0));
            g.fillOval(appleX, appleY, tileSize, tileSize);
        }
        // draw snake
        if (running) {
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
        else if (blinkBlack){      // draw blinking death
            for (int i=0; i<bodyParts; i++) {
                g.setColor(new Color(0, 0, 0));
                g.fillRect(snakeXArray[i], snakeYArray[i], tileSize, tileSize);
            }
        }
        else if (!blinkBlack) {
            for (int i=0; i<bodyParts; i++) {
                g.setColor(new Color(0, 130, 0));
                g.fillRect(snakeXArray[i], snakeYArray[i], tileSize, tileSize);
            }
        }
    }

    public void setup() {
        display.setVisible(false);
        bodyParts = 5;
        int randSpawn = randRange(10, 21);
        randDirection(0, 4);
        for (int i=0; i<bodyParts; i++) {
            snakeXArray[i] = (i*tileSize)+(tileSize*randSpawn);
            snakeYArray[i] = (tileSize * randSpawn);
        }
        getApple();
        timer.setDelay(250);
        timer.stop();
        won = false;
        score = 0;
        time = 0;
        running = true;
        gotInput = true;
        blinkAmount = 1;
        repaint();
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
        for (int j = 1; j<bodyParts; j++) {     // if snake hits itself
            if (snakeXArray[0] == snakeXArray[j] && snakeYArray[0] == snakeYArray[j]) {
                running = false;
            }
        }
        for (int i = 0; i<bodyParts; i++) {     // if snake hits edge of window
            if (snakeXArray[i] < 0 || snakeXArray[i] >= windowSize || snakeYArray[i] < 0 || snakeYArray[i] >= windowSize) {
                running = false;
            }
        }
        if (snakeXArray[0] == appleX && snakeYArray[0] == appleY) {     // if snake eats apple
            playSound(appleSound);
            eaten = true;
            if (timerDelay <= 50) {
            }
            else {
                timerDelay -= 10;
                timer.setDelay((int) timerDelay);    
            }
            score++;
            bodyParts++;
            addBody();
            getApple();
        }
    }
    
    public void move() {    // move the snake
        switch (direction) {
            case 0:
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
            time += timerDelay/1000;
            move();
            gotInput = false;
        }
        else {
            timer.setDelay(100);
            gameOver();
        }
    }

    public void addBody() {
        if (bodyParts == arraySize) {
            running = false;
            display.setVisible(true);
            display.setText("You Win!");
            won = true;
            wins++;
        }
        else {
            snakeXArray[bodyParts-1] = prevSnakeXArray[bodyParts-1];
            snakeYArray[bodyParts-1] = prevSnakeYArray[bodyParts-1];
        }
    }

    public void playSound(File sound) {     // dont know how tf i made this work but uh yea it plays sounds
        try {
            audioStream = AudioSystem.getAudioInputStream(sound);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {

        }
    }

    public void gameOver() {
        if (!won) {
            if (blinkAmount == 1) { 
                playSound(deathSound);
            }
            if (blinkAmount < 8) {
                if (blinkAmount % 2 == 1) {
                    blinkBlack = true;
                }
                else { 
                    blinkBlack = false;
                }
                blinkAmount++;
                repaint();
            }
            else {
                timer.stop();
                display.setVisible(true);
                display.setText("Game Over!");
            }
        }
        else {
            timer.stop();
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
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && running == true && timer.isRunning() == false) {
            timer.start();
        }

    }
    public void keyTyped(KeyEvent event) {

    }

    public int randRange(int min, int max) {
        return rand.ints(min, max).findFirst().getAsInt();
    }

    public void randDirection(int min, int max) {
        direction = rand.ints(min, max).findFirst().getAsInt();
        if (direction == 1) {
            randDirection(min, max);
        }
    }

    public int getScore() {
        return score;
    }

    public double getTime() {
        return time;
    }

    public int getWins() {
        return wins;
    }
}