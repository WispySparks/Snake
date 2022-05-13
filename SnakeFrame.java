package SnakeGame;
import SnakeGame.SnakePanel;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class SnakeFrame extends JFrame{
    SnakeFrame() {
        SnakePanel panel = new SnakePanel();
        this.add(panel);
        this.addKeyListener(panel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
