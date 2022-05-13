package SnakeGame;
import SnakeGame.SnakePanel;
import javax.swing.JFrame;
import java.awt.*;

public class SnakeFrame extends JFrame{
    SnakeFrame() {
        this.add(new SnakePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.black);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
